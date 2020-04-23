package com.example.springdemo.exception;

public class UserException extends RuntimeException{
    private static final long serialVersionUID = -5040910077953180233L;

    public UserException(final String errorMessage) {
        super(errorMessage);
    }
}
