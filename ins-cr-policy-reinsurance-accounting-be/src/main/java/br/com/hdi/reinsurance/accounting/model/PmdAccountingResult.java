package br.com.hdi.reinsurance.accounting.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PmdAccountingResult {
    private final String message;
    private final int affectedRows;
    private final String success;
    private final HttpStatus status;
}
