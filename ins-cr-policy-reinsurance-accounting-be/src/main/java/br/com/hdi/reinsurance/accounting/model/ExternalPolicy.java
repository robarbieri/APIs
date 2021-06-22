package br.com.hdi.reinsurance.accounting.model;

import java.util.Objects;
import javax.validation.constraints.*;

public class ExternalPolicy {
    private Long id = null;
    private String code = null;
    private Integer endorsementNumber = null;
    //  private Integer invoiceNumber = null;
    private Integer orderNumber = null;

    public ExternalPolicy id(Long id) {
        this.id = id;
        return this;
    }

    @NotNull(message = "{id.not.null}")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExternalPolicy code(String code) {
        this.code = code;
        return this;
    }

    @NotNull(message = "{policycode.not.null}")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExternalPolicy endorsementNumber(Integer endorsementNumber) {
        this.endorsementNumber = endorsementNumber;
        return this;
    }

    @NotNull(message = "{endorsementnumber.not.null}")
    public Integer getEndorsementNumber() {
        return endorsementNumber;
    }

    public void setEndorsementNumber(Integer endorsementNumber) {
        this.endorsementNumber = endorsementNumber;
    }

//  public ExternalPolicy invoiceNumber(Integer invoiceNumber) {
//    this.invoiceNumber = invoiceNumber;
//    return this;
//  }
//
//  @NotNull(message = "{invoicenumber.not.null}")
//  public Integer getInvoiceNumber() {
//    return invoiceNumber;
//  }
//
//  public void setInvoiceNumber(Integer invoiceNumber) {
//    this.invoiceNumber = invoiceNumber;
//  }

    public ExternalPolicy orderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    @NotNull(message = "{ordernumber.not.null}")
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalPolicy externalPolicy = (ExternalPolicy) o;
        return Objects.equals(this.id, externalPolicy.id) &&
                Objects.equals(this.code, externalPolicy.code) &&
                Objects.equals(this.endorsementNumber, externalPolicy.endorsementNumber) &&
//        Objects.equals(this.invoiceNumber, externalPolicy.invoiceNumber) &&
                Objects.equals(this.orderNumber, externalPolicy.orderNumber);
    }

    @Override
//  public int hashCode() {
//    return Objects.hash(id, code, endorsementNumber, invoiceNumber, orderNumber);
//  }
    public int hashCode() {
        return Objects.hash(id, code, endorsementNumber, orderNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalPolicy {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    endorsementNumber: ").append(toIndentedString(endorsementNumber)).append("\n");
//    sb.append("    invoiceNumber: ").append(toIndentedString(invoiceNumber)).append("\n");
        sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
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