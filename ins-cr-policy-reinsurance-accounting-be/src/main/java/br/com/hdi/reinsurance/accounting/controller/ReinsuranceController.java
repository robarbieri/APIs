package br.com.hdi.reinsurance.accounting.controller;

import br.com.hdi.reinsurance.accounting.model.PmdAccountingResult;
import br.com.hdi.reinsurance.accounting.model.accounting.AccountingEvents;
import br.com.hdi.reinsurance.accounting.service.ReinsuranceService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/", consumes = {"*"}, produces = {"application/json"})
public class ReinsuranceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReinsuranceService service;

    @PostMapping(path = "/", name = "processPmdAccounting")
    public ResponseEntity<PmdAccountingResult> getAccounting(
            @RequestHeader(name = "X-Company-Id", required = true) Integer xCompanyId,
            @RequestHeader(name = "X-Application-Id", required = true) Integer xApplicationId,
            @RequestHeader(name = "X-User-Id", required = true) String xUserId,
            @RequestHeader(name = "X-Trace-Id", required = true) String xTraceId,
            @RequestHeader("Authorization") String token,
            @RequestParam("key") String key
            //@Valid @RequestBody AccountingEvents accountingEvents
    ) throws Exception {
        logger.info("Business Validation - REQUEST PARAMS: xCompanyId:{} | xApplicationId:{} | xUserId:{} | xTraceId:{}",
                xCompanyId, xApplicationId, xUserId, xTraceId);


        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Company-Id", xCompanyId.toString());
        headers.add("X-Application-Id", xApplicationId.toString());
        headers.add("X-User-Id", xUserId);
        final String traceId = StringUtils.isBlank(xTraceId) ? UUID.randomUUID().toString() : xTraceId;
        headers.add("X-Trace-Id", traceId);
        headers.add("Authorization", token);

        //String obj = service.testMethod();
        return new ResponseEntity<>(service.processPmdAccounting(headers, token, key), HttpStatus.OK);
    }
}


