package br.com.hdi.reinsurance.accounting.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClosingDate {
    @JsonProperty("REFER_MES")
    private int numMes;
    @JsonProperty("REFER_ANO")
    private int numAno;
}
