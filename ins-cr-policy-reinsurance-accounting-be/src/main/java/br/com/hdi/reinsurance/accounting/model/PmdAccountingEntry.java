package br.com.hdi.reinsurance.accounting.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@EqualsAndHashCode
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PmdAccountingEntry {
    @JsonProperty("CPR_CONTRATO")
    private String numContrato;
    @JsonProperty("CPR_EMPRESA")
    private String codEmpresa;
    @JsonProperty("CPR_SUCURSAL")
    private String codSucursal;
    @JsonProperty("CPR_CARTEIRA")
    private String codCarteira;
    @JsonProperty("CPR_RAMO_ITEM")
    private String codRamoItem;
    @JsonProperty("CPR_UF")
    private String uf;
    @JsonProperty("CPR_MES")
    private int numMes;
    @JsonProperty("CPR_ANO")
    private int numAno;
    @JsonProperty("VR_PROVISAO")
    private BigDecimal vlrProvisao;
    @JsonProperty("CPR_PAR_COD_SUSEP")
    private String codSusepParticipante;
    @JsonProperty("PAR_TIPO_ORIGEM")
    private String codTipOrigem;

}
