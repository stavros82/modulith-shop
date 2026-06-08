package com.example.orders.exception;

import java.util.Collections;
import java.util.List;

/**
 * Thrown when one or more business rule validations fail in the domain.
 */
public class BusinessValidationException extends BusinessException {

    private final List<String> errors;

    public BusinessValidationException(String message) {
        super(message);
        this.errors = Collections.emptyList();
    }

    public BusinessValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors == null ? Collections.emptyList() : List.copyOf(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}

