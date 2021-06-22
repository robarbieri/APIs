package br.com.hdi.reinsurance.accounting.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import br.com.hdi.reinsurance.accounting.handle.ApiException;
import br.com.hdi.reinsurance.accounting.model.PmdAccountingEntry;
import br.com.hdi.reinsurance.accounting.model.mapper.PmdAccountingEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ReinsuranceRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSourceHdi;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSourceHdi);
    }

    //	Funções de pesquisa
    public List<PmdAccountingEntry> findAccounting()
            throws ApiException {
        try {
            return getJdbcTemplate().query(QueriesRepository.SELECT_CONTABILIZACAO,
                    new Object[]{}, new PmdAccountingEntryMapper());

        } catch (Exception e) {
            throw new ApiException(400, "Erro ao recuperar AppProd.", e.getMessage(), null, e);
        }
    }

    //	Funções de inserção
    public void isBegin() throws ApiException {
        try {
            getJdbcTemplate().execute(QueriesRepository.INSERT_BEGIN);
        } catch (Exception e) {
            throw new ApiException(400, "Erro ao abrir transação na base de dados.", e.getMessage(), null, e);
        }
    }

    public void isCommit() throws ApiException {
        try {
            getJdbcTemplate().execute(QueriesRepository.INSERT_COMMIT);
        } catch (Exception e) {
            throw new ApiException(400, "Erro ao comitar transação na base de dados.", e.getMessage(), null, e);
        }
    }

    public void isRollback() throws ApiException {
        try {
            getJdbcTemplate().execute(QueriesRepository.INSERT_ROLLBACK);
        } catch (Exception e) {
            throw new ApiException(400, "Erro ao dar rollback na base de dados.", e.getMessage(), null, e);
        }
    }

}
