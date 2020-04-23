package com.example.springdemo.errorhandler;

import java.util.List;

/**
 * example
 */
public class EntityValidationException extends RuntimeException {

    private static final String MESSAGE = "BaseEntity could not be processed !";

    private final String resource;
    private final List<String> validationErrors;

    public EntityValidationException(String resource, List<String> errors) {
        super(MESSAGE);
        this.resource = resource;
        this.validationErrors = errors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public String getResource() {
        return resource;
    }
}