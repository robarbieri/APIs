package br.com.hdi.reinsurance.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PmdAccounting {
    List<PmdAccountingEntry> accountingEntries;
}
