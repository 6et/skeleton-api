package com.sixet.skeleton.core.exception.response;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<ValidationErrorFieldResponseData> fieldErrors = new ArrayList<>();
    private String errorMessageCode;

    /**
     * Add field error.
     *
     * @param path    Resource field
     * @param message Error message
     */
    public void addFieldError(String path, String message) {
        ValidationErrorFieldResponseData error = new ValidationErrorFieldResponseData(path, message);
        fieldErrors.add(error);
    }

    /**
     * Get errors list.
     *
     * @return A list of errors
     */
    public List<ValidationErrorFieldResponseData> getFieldErrors() {
        return fieldErrors;
    }

    public String getErrorMessageCode() {
        return errorMessageCode;
    }

    public void setErrorMessageCode(String errorMessageCode) {
        this.errorMessageCode = errorMessageCode;
    }
}
