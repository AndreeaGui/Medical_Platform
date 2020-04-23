package com.example.springdemo.exception;

public class MedicationExceeption extends RuntimeException{
    private static final long serialVersionUID = -5040910177953180233L;

    public MedicationExceeption(final String errorMessage) {
        super(errorMessage);
    }
}
