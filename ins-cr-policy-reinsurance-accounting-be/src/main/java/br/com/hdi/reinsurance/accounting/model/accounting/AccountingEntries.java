package br.com.hdi.reinsurance.accounting.model.accounting;

import br.com.hdi.reinsurance.accounting.model.accounting.Currency;
import br.com.hdi.reinsurance.accounting.model.accounting.Line;
import br.com.hdi.reinsurance.accounting.model.accounting.CostCenter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class AccountingEntries {
    private String operationDescription = null;
    private BigDecimal amountValue = null;
    private Currency currency = null;
    private Line line = null;
    private Bank bank = null;
    private CostCenter costCenter = null;

    public AccountingEntries operationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
        return this;
    }

    @NotNull(message = "{operationDescription.not.null}")
    @Valid
    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public AccountingEntries amountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
        return this;
    }

    @NotNull(message = "{amountValue.not.null}")
    @Valid
    public BigDecimal getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

    public AccountingEntries currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    @NotNull(message = "{json.not.null}")
    @Valid
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountingEntries line(Line line) {
        this.line = line;
        return this;
    }

    @NotNull(message = "{json.not.null}")
    @Valid
    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public AccountingEntries bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    @NotNull(message = "{json.not.null}")
    @Valid
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public AccountingEntries costCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
        return this;
    }

    @NotNull(message = "{json.not.null}")
    @Valid
    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountingEntries accountingEntries = (AccountingEntries) o;
        return Objects.equals(this.operationDescription, accountingEntries.operationDescription) &&
                Objects.equals(this.amountValue, accountingEntries.amountValue) &&
                Objects.equals(this.currency, accountingEntries.currency) &&
                Objects.equals(this.line, accountingEntries.line) &&
                Objects.equals(this.bank, accountingEntries.bank) &&
                Objects.equals(this.costCenter, accountingEntries.costCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationDescription, amountValue, currency, line, bank, costCenter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoInsurencePolicy {\n");

        sb.append("    operationDescription: ").append(toIndentedString(operationDescription)).append("\n");
        sb.append("    amountValue: ").append(toIndentedString(amountValue)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    line: ").append(toIndentedString(line)).append("\n");
        sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
        sb.append("    costCenter: ").append(toIndentedString(costCenter)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
