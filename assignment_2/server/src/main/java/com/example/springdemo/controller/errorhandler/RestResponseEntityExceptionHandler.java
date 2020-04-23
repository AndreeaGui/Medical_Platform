package com.example.springdemo.controller.errorhandler;

import com.example.springdemo.errorhandler.EntityValidationException;
import com.example.springdemo.errorhandler.ExceptionHandlerResponseDTO;
import com.example.springdemo.errorhandler.IncorrectParameterException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * example
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {EntityValidationException.class})
    protected ResponseEntity<Object> handleEntityValidationExceptionConflict(RuntimeException ex, WebRequest request) {
        if (!(ex instanceof EntityValidationException)){
            return handleExceptionInternal(
                    ex,
                    null,
                    new HttpHeaders(),
                    HttpStatus.CONFLICT,
                    request
            );
        }

        EntityValidationException customEx = (EntityValidationException)ex;
        List<String> details= customEx.getValidationErrors();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ExceptionHandlerResponseDTO exceptionHandlerResponseDTO = new ExceptionHandlerResponseDTO(customEx.getResource(), status, details, request.getDescription(false));
        return handleExceptionInternal(
                ex,
                exceptionHandlerResponseDTO,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        if (!(ex instanceof ResourceNotFoundException)){
            return handleExceptionInternal(
                    ex,
                    null,
                    new HttpHeaders(),
                    HttpStatus.CONFLICT,
                    request
            );
        }

        ResourceNotFoundException customEx = (ResourceNotFoundException)ex;
        List<String> details= new ArrayList<>();
        details.add(customEx.getMessage());

        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionHandlerResponseDTO exceptionHandlerResponseDTO = new ExceptionHandlerResponseDTO(customEx.getResourceName(), status, details, request.getDescription(false));
        return handleExceptionInternal(
                ex,
                exceptionHandlerResponseDTO,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }


    @ExceptionHandler(value = {IncorrectParameterException.class})
    protected ResponseEntity<Object> handleIncorrectParameterException(RuntimeException ex, WebRequest request) {

        if (!(ex instanceof IncorrectParameterException)){
            return handleExceptionInternal(
                    ex,
                    null,
                    new HttpHeaders(),
                    HttpStatus.CONFLICT,
                    request
            );
        }

        IncorrectParameterException customEx = (IncorrectParameterException)ex;
        List<String> details= customEx.getInvalidParams();
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;


        ExceptionHandlerResponseDTO exceptionHandlerResponseDTO = new ExceptionHandlerResponseDTO(customEx.getResource(), status, details, request.getDescription(false));
        return handleExceptionInternal(
                ex,
                exceptionHandlerResponseDTO,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }
}
