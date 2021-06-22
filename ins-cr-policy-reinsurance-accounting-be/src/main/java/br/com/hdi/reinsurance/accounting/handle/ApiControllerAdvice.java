package br.com.hdi.reinsurance.accounting.handle;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.resource.HttpResource;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiRetorno> handleApiException(ApiException ex) {
        return new ResponseEntity<ApiRetorno>(ex.getApiRetorno(),
                HttpStatus.valueOf(Integer.parseInt(ex.getApiRetorno().getCode())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRetorno> handleException(Exception ex) {
        ApiException apiException = new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), ex.getLocalizedMessage(), "", ex);
        return new ResponseEntity<ApiRetorno>(apiException.getApiRetorno(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiRetorno> handleInternalError(HttpRequest req, HttpResource res, Exception ex) {
        ApiException apiException = new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), ex.getLocalizedMessage(), "", ex);
        return new ResponseEntity<ApiRetorno>(apiException.getApiRetorno(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiRetorno> handleBadRequest(HttpRequest req, HttpResource res, BadRequest ex) {
        ApiException apiException = new ApiException(
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST", ex.getResponseBodyAsString(), "", ex);
        return new ResponseEntity<ApiRetorno>(apiException.getApiRetorno(),
                HttpStatus.BAD_REQUEST);
    }

}