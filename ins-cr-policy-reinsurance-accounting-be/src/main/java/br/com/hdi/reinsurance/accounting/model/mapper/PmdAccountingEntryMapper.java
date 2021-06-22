package br.com.hdi.reinsurance.accounting.model.mapper;

import br.com.hdi.reinsurance.accounting.model.PmdAccountingEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PmdAccountingEntryMapper implements RowMapper<PmdAccountingEntry> {

    @Override
    public PmdAccountingEntry mapRow(ResultSet resultSet, int i) throws SQLException {
        PmdAccountingEntry pmd = new PmdAccountingEntry();


//        pmd.setCPR_CONTRATO(resultSet.getString("CPR_Contrato"));
//        pmd.setCPR_SUCURSAL(resultSet.getString("CPR_Sucursal"));
//        pmd.setCPR_CARTEIRA(resultSet.getString("CPR_Carteira"));
//        pmd.setCPR_RAMO_ITEM(resultSet.getString("CPR_Ramo_Item"));
//        pmd.setCPR_PAR_COD_SUSEP(resultSet.getString("CPR_Par_Cod_Susep"));
//        pmd.setCPR_UF(resultSet.getString("CPR_Uf"));
//        pmd.setCPR_MES(resultSet.getString("CPR_Mes"));
//        pmd.setCPR_ANO(resultSet.getString("CPR_Ano"));
//        pmd.setVR_PROVISAO(resultSet.getBigDecimal("VR_PROVISAO"));
//        pmd.setCPR_EMPRESA(resultSet.getString("CPR_EMPRESA"));

        return pmd;
    }
}
