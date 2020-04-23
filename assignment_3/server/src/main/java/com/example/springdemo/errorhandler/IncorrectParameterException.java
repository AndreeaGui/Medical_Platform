package com.example.springdemo.errorhandler;

import java.util.List;

/**
 * example
 */
public class IncorrectParameterException extends RuntimeException {

    private static final String MESSAGE = "Incorrect Parameters";
    private final String resource;
    private final List<String> invalidParams;

    public IncorrectParameterException(String resource, List<String> errors) {
        super(MESSAGE);
        this.resource = resource;
        this.invalidParams = errors;
    }

    public List<String> getInvalidParams() {
        return invalidParams;

    }

    public String getResource() {
        return resource;
    }
}