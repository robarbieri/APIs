package br.com.hdi.reinsurance.accounting.handle;

import java.util.ArrayList;
import java.util.List;

public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;
    private ApiRetorno apiRetorno = null;

    public ApiException(Integer code, String message, String nativeMessage, String traceId, Exception ex) {
        super(nativeMessage, ex);
        ex.printStackTrace();
        setApiRetorno(code, message, traceId);
    }

    public ApiRetorno getApiRetorno() {
        return apiRetorno;
    }

    public void setApiRetorno(ApiRetorno apiRetorno) {
        this.apiRetorno = apiRetorno;
    }

    private void setApiRetorno(Integer code, String message, String traceId) {

        List<Error> errors = new ArrayList<>();

        StringBuilder description = new StringBuilder();
        description.append(this.getMessage() + ": ");
        for (StackTraceElement var : this.getStackTrace()) {
            description.append(var.getFileName())
                    .append(" - ")
                    .append(var.getClassName())
                    .append(" (")
                    .append(var.getMethodName())
                    .append(":")
                    .append(var.getLineNumber())
                    .append(")");
        }

        errors.add(new Error(String.valueOf(this.hashCode()),
                this.getMessage(), description.toString()));

        Throwable cause = this.getCause();
        while (cause != null) {
            StringBuilder description1 = new StringBuilder();
            description.append(this.getMessage() + ": ");
            for (StackTraceElement var : this.getStackTrace()) {
                description1.append(var.getFileName())
                        .append(" - ")
                        .append(var.getClassName())
                        .append(" (")
                        .append(var.getMethodName())
                        .append(":")
                        .append(var.getLineNumber())
                        .append(")\n");
            }

            errors.add(new Error(String.valueOf(cause.hashCode()),
                    cause.getMessage(), description1.toString()));
            cause = cause.getCause();
        }

        apiRetorno = new ApiRetorno(String.valueOf(code), message,
                this.getMessage(), traceId, errors);
    }

}