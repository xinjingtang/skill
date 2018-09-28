package com.group.integrate.excepiton;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "errorCode", "validationErrors", "data"})
public class ExceptionDetail {

    private String message;
    private String errorCode;
    private String bizErrorCode;
    private List<ValidationError> validationErrors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ValidationError errorCode) {
        this.errorCode = errorCode.getExternalPropertyName();
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String getBizErrorCode() {
        return bizErrorCode;
    }

    public void setBizErrorCode(String bizErrorCode) {
        this.bizErrorCode = bizErrorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
