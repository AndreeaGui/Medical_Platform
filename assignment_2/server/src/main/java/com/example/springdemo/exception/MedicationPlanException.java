package com.example.springdemo.exception;

public class MedicationPlanException extends RuntimeException{
    private static final long serialVersionUID = -5040910177953180233L;

    public MedicationPlanException(final String errorMessage) {
        super(errorMessage);
    }
}

