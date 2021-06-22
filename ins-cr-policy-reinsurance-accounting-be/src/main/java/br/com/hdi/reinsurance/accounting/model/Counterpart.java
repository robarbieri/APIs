package br.com.hdi.reinsurance.accounting.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class Counterpart {
    private String code = null;
    private OrganizationBranch organizationBranch = null;

    public Counterpart code(String code) {
        this.code = code;
        return this;
    }

    @NotNull(message = "{counterpartcode.not.null}")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Counterpart organizationBranch(OrganizationBranch organizationBranch) {
        this.organizationBranch = organizationBranch;
        return this;
    }

    @NotNull(message = "{counterpartorganizationbranch.not.null}")
    @Valid
    public OrganizationBranch getOrganizationBranch() {
        return organizationBranch;
    }

    public void setOrganizationBranch(OrganizationBranch organizationBranch) {
        this.organizationBranch = organizationBranch;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counterpart counterpart = (Counterpart) o;
        return Objects.equals(this.code, counterpart.code) &&
                Objects.equals(this.organizationBranch, counterpart.organizationBranch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, organizationBranch);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Counterpart {\n");

        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    organizationBranch: ").append(toIndentedString(organizationBranch)).append("\n");
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