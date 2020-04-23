package com.example.springdemo.validators;
/**
 * example
 */
public interface FieldValidator<T> {
     boolean validate(T t);
}
