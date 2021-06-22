package br.com.hdi.reinsurance.accounting.service;

import br.com.hdi.reinsurance.accounting.handle.ApiException;
import br.com.hdi.reinsurance.accounting.model.*;
import br.com.hdi.reinsurance.accounting.model.accounting.*;
import br.com.hdi.reinsurance.accounting.repository.ReinsuranceRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReinsuranceService {

    @Value("${app.operation.pmd.admitted:}")
    private String OPERATION_PMD_ADMITTED;

    @Value("${app.operation.pmd.local:}")
    private String OPERATION_PMD_LOCAL;

    @Value("${app.operation.pmd.eventual:}")
    private String OPERATION_PMD_EVENTUAL;

    @Value("${app.url.accounting:}")
    private String URL_ACC_SCC;

    @Value("${app.url.queryconnector:}")
    private String URL_QUERY_CONNECTOR;

    @Value("${app.event.accounting.provision:}")
    private String EVENT_ACC_CIR_PROVISION;


    // Query Connector params
    @Value("${app.queryconnector.referdate.code:}")
    private String QUERY_CONNECTOR_REFER_DATE_CODE;

    @Value("${app.queryconnector.pmd.code:}")
    private String QUERY_CONNECTOR_PMDQUERY_CODE;

    @Autowired
    ReinsuranceRepository reinsuranceRepository;

    @Autowired
    RestTemplate restTemplate;

    public PmdAccountingResult processPmdAccounting(HttpHeaders headers,
                                                    String token,
                                                    String key) throws Exception {
        log.info("> Processing PMD Accounting...");
        try {
            PmdAccounting pmdAccounting = new PmdAccounting();
            AccountingEvents accountingEvents = new AccountingEvents();
            List<AccountingEntries> accountingEntries = new ArrayList<>();
            List<AccountingEvents> acumulatedAccounting = new ArrayList<>();
            HttpEntity<PmdAccounting> entity = new HttpEntity<>(headers);

            log.info("> [START] Getting param date to filter PMD ...");
            ResponseEntity<List<ClosingDate>> closingDateResponse = restTemplate.exchange(
                    URL_QUERY_CONNECTOR + "?code={queryCode}", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ClosingDate>>() {
                    }, QUERY_CONNECTOR_REFER_DATE_CODE);
            log.info("> [END] Getting param date to filter PMD ...");

            ClosingDate referDate = closingDateResponse.getBody().get(0);


            log.info("> [START] Getting PMD via Query Connector ...");

            Map<String, String> uriVariables = new HashMap<>();

            int currentYear, currentMonth, lastMonth, lastYear;
//            uriVariables.put("queryCode", QUERY_CONNECTOR_PMDQUERY_CODE);
//            uriVariables.put("currentYear", Integer.toString(referDate.getNumAno()));
//            uriVariables.put("currentMonth", Integer.toString(referDate.getNumMes()));
//            uriVariables.put("lastMonth", Integer.toString(referDate.getNumMes() - 1));


            uriVariables.put("queryCode", QUERY_CONNECTOR_PMDQUERY_CODE);
            uriVariables.put("currentYear", Integer.toString(referDate.getNumAno()));
            uriVariables.put("currentMonth", "6");
            uriVariables.put("lastMonth", "5");

            currentYear = referDate.getNumAno();
            currentMonth = 6;//referDate.getNumMes();
            lastMonth = referDate.getNumMes() - 1;
            lastYear = currentYear;
            // Trata fim de ano 
            if (referDate.getNumMes() == 1) {
                lastYear = currentYear - 1;
                lastMonth = 12;
            }

            ResponseEntity<List<PmdAccountingEntry>> response = restTemplate.exchange(
                    URL_QUERY_CONNECTOR + "?code={queryCode}&xYear={currentYear}&xMonth={currentMonth}&xLastMonth={lastMonth}", HttpMethod.GET, entity, new ParameterizedTypeReference<List<PmdAccountingEntry>>() {
                    }, uriVariables);
            log.info("> [END] Getting PMD via Query Connector ...");


            // Carrega lista de PMDS
            final List<PmdAccountingEntry> pmds = response.getBody();
            // Crio cópia para filtro
            List<PmdAccountingEntry> pmdsCopy = response.getBody();

            assert pmdsCopy != null;
            assert pmds != null;

            for (PmdAccountingEntry pmd : pmds) {
                reinsuranceRepository.isBegin();
                String accountingOperation = "";
                // Reseta lista para próximo registro
                accountingEntries.clear();

                // Gera linha de contabilizaçao, caso tenha valor de Provisão e seja processamento do mês/ano atual
                if (pmd.getVlrProvisao().compareTo(BigDecimal.valueOf(0)) != 0 && (pmd.getNumMes() == currentMonth && pmd.getNumAno() == currentYear)) {
                    switch (pmd.getCodTipOrigem()) {
                        case "1": // Local (L)
                            accountingOperation = (OPERATION_PMD_LOCAL);
                            break;
                        case "2": // Eventual (E)
                            accountingOperation = (OPERATION_PMD_EVENTUAL);
                            break;
                        case "3": // Admitida (A)
                            accountingOperation = (OPERATION_PMD_ADMITTED);
                            break;
                        default:
                            accountingOperation = null;
                    }
                    if (accountingOperation == null) {
                        String messageError = "Tipo de ressegurador " + pmd.getCodTipOrigem() + " não existe.";
                        throw new ApiException(HttpStatus.NOT_ACCEPTABLE.value(), messageError, null, null,
                                new Exception(messageError));
                    }

                    accountingEntries.add(new AccountingEntries()
                            .operationDescription(accountingOperation)
                            .amountValue(pmd.getVlrProvisao())
                            .currency(new Currency().code(null))
                            .line(new Line().code(null))
                            .bank(new Bank().code(null))
                            .costCenter(new CostCenter().code(null))
                    );
                }
                // Caso tenha linhas, monto payload e chamo API de Contabilização
                if (accountingEntries.size() > 0) {
                    log.info("> Processing entries {}...", acumulatedAccounting.size() + 1);

                    accountingEvents.setOriginType("C"); // TODO - Confirmar INFORMAÇÕES da Apólice
                    accountingEvents.setCompany(new Company().code(pmd.getCodEmpresa()));
                    accountingEvents.setOrganizationBranch(new OrganizationBranch().code(pmd.getCodSucursal()));
                    accountingEvents.setPortfolio(new Portfolio().code(pmd.getCodCarteira()));
                    accountingEvents.setCounterpart(
                            new Counterpart().code("6572").organizationBranch(new OrganizationBranch().code("1"))); // Sucursal Congênere
                    accountingEvents.setDocumentType("1");
                    accountingEvents.setSourceDocumentNumber(Integer.parseInt(pmd.getNumContrato()));
                    accountingEvents.setEventCode(EVENT_ACC_CIR_PROVISION);
                    accountingEvents.setEventDate(LocalDate.now());

                    accountingEvents.setAccountingEntries(accountingEntries);
                    // PubSub.publish(payload)                    restTemplate.postForObject(URL_ACC_SCC + "?key=" + key, new HttpEntity<>(accountingEvents, headers), String.class);
                    log.info("> [START] Updating database flag on row {}...", acumulatedAccounting.size() + 1);
                    // TODO - Trecho que atualizará o campo de 'Contabilizado' da tabela de PMD (Definir Flags de status)
                    // 1 - Contabilizado
                    // 2 - Na fila
                    // 3 -
                    log.info("> [END]   Updating database flag on row {}...", acumulatedAccounting.size() + 1);
                    acumulatedAccounting.add(accountingEvents);
                    reinsuranceRepository.isCommit();
                }
            }

            log.info("Successfully generated accounting for {} rows", acumulatedAccounting.size());

            return new PmdAccountingResult("Everything OK", acumulatedAccounting.size(), "Success", HttpStatus.OK);
        } catch (HttpStatusCodeException h) {
            reinsuranceRepository.isRollback();
            // Monto JSON e pego só a mensagem
            String jsonMessage = new JSONObject(h.getResponseBodyAsString()).getString("message");

            throw new ApiException(HttpStatus.BAD_REQUEST.value(), jsonMessage, h.getLocalizedMessage(), null, h);
        } catch (Exception e) {
            reinsuranceRepository.isRollback();
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), e.getLocalizedMessage(), null, e);
        }

    }
}