package br.com.hdi.reinsurance.accounting.model.accounting;

import br.com.hdi.reinsurance.accounting.model.Company;
import br.com.hdi.reinsurance.accounting.model.Counterpart;
import br.com.hdi.reinsurance.accounting.model.OrganizationBranch;
import br.com.hdi.reinsurance.accounting.model.Portfolio;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Validated
public class AccountingEvents {
    private Object originType = null;
    private Company company = null;
    private OrganizationBranch organizationBranch = null;
    private Portfolio portfolio = null;
    private Counterpart counterpart = null;
    private Object documentType = null;
    private Integer sourceDocumentNumber = null;
    private String eventCode = null;
    private LocalDate eventDate = null;
    @Valid
    private List<AccountingEntries> accountingEntries = new ArrayList<AccountingEntries>();

    public AccountingEvents originType(Object originType) {
        this.originType = originType;
        return this;
    }

    @NotNull(message = "{origintype.not.null}")
    public Object getOriginType() {
        return originType;
    }

    public void setOriginType(Object originType) {
        this.originType = originType;
    }

    public AccountingEvents company(Company company) {
        this.company = company;
        return this;
    }

    @NotNull(message = "{company.not.null}")
    @Valid
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AccountingEvents organizationBranch(OrganizationBranch organizationBranch) {
        this.organizationBranch = organizationBranch;
        return this;
    }

    @NotNull(message = "{organizationbranch.not.null}")
    @Valid
    public OrganizationBranch getOrganizationBranch() {
        return organizationBranch;
    }

    public void setOrganizationBranch(OrganizationBranch organizationBranch) {
        this.organizationBranch = organizationBranch;
    }

    public AccountingEvents portfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        return this;
    }

    @NotNull(message = "{portfolio.not.null}")
    @Valid
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public AccountingEvents counterpart(Counterpart counterpart) {
        this.counterpart = counterpart;
        return this;
    }

    @NotNull(message = "{counterpart.not.null}")
    @Valid
    public Counterpart getCounterpart() {
        return counterpart;
    }

    public void setCounterpart(Counterpart counterpart) {
        this.counterpart = counterpart;
    }

    public AccountingEvents documentType(Object documentType) {
        this.documentType = documentType;
        return this;
    }

    @NotNull(message = "{documentType.not.null}")
    @Valid
    public Object getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Object documentType) {
        this.documentType = documentType;
    }

    public AccountingEvents sourceDocumentNumber(Integer sourceDocumentNumber) {
        this.sourceDocumentNumber = sourceDocumentNumber;
        return this;
    }

    @NotNull(message = "{sourceDocumentNumber.not.null}")
    @Valid
    public Integer getSourceDocumentNumber() {
        return sourceDocumentNumber;
    }

    public void setSourceDocumentNumber(Integer sourceDocumentNumber) {
        this.sourceDocumentNumber = sourceDocumentNumber;
    }

    public AccountingEvents eventCode(String eventCode) {
        this.eventCode = eventCode;
        return this;
    }

    @NotNull(message = "{eventCode.not.null}")
    @Valid
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public AccountingEvents eventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    @NotNull(message = "{eventDate.not.null}")
    @Valid
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public AccountingEvents accountingEntries(List<AccountingEntries> accountingEntries) {
        this.accountingEntries = accountingEntries;
        return this;
    }

    public AccountingEvents addAccountingEntriesItem(AccountingEntries accountingEntriesItem) {
        this.accountingEntries.add(accountingEntriesItem);
        return this;
    }

    @NotEmpty(message = "{accountingEntries.not.null}")
    @Valid
    public List<AccountingEntries> getAccountingEntries() {
        return accountingEntries;
    }

    public void setAccountingEntries(List<AccountingEntries> accountingEntries) {
        this.accountingEntries = accountingEntries;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountingEvents accountingEvents = (AccountingEvents) o;
        return Objects.equals(this.originType, accountingEvents.originType) &&
                Objects.equals(this.company, accountingEvents.company) &&
                Objects.equals(this.organizationBranch, accountingEvents.organizationBranch) &&
                Objects.equals(this.portfolio, accountingEvents.portfolio) &&
                Objects.equals(this.counterpart, accountingEvents.counterpart) &&
                Objects.equals(this.documentType, accountingEvents.documentType) &&
                Objects.equals(this.sourceDocumentNumber, accountingEvents.sourceDocumentNumber) &&
                Objects.equals(this.eventCode, accountingEvents.eventCode) &&
                Objects.equals(this.eventDate, accountingEvents.eventDate) &&
                Objects.equals(this.accountingEntries, accountingEvents.accountingEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originType, company, organizationBranch, portfolio, counterpart, documentType, sourceDocumentNumber, eventCode, eventDate, accountingEntries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoInsurencePolicy {\n");

        sb.append("    originType: ").append(toIndentedString(originType)).append("\n");
        sb.append("    company: ").append(toIndentedString(company)).append("\n");
        sb.append("    organizationBranch: ").append(toIndentedString(organizationBranch)).append("\n");
        sb.append("    portfolio: ").append(toIndentedString(portfolio)).append("\n");
        sb.append("    counterpart: ").append(toIndentedString(counterpart)).append("\n");
        sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
        sb.append("    sourceDocumentNumber: ").append(toIndentedString(sourceDocumentNumber)).append("\n");
        sb.append("    eventCode: ").append(toIndentedString(eventCode)).append("\n");
        sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
        sb.append("    accountingEntries: ").append(toIndentedString(accountingEntries)).append("\n");
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