package com.sixet.skeleton.core.exception;

import com.sixet.skeleton.core.exception.response.ValidationErrorResponse;

/**
 *
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -107061383836933531L;

    private final ValidationErrorResponse errors;

    public ValidationException(final ValidationErrorResponse errors) {
        super();
        this.errors = errors;
    }

    /**
     * Get errors list.
     *
     * @return the erros
     */
    public ValidationErrorResponse getErrors() {
        return this.errors;
    }
}
