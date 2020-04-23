package com.example.springdemo.validators;

import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.errorhandler.IncorrectParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
/**
 * example
 */
public class PersonFieldValidator{

    private static final Log LOGGER = LogFactory.getLog(PersonFieldValidator.class);
    private static final EmailFieldValidator EMAIL_VALIDATOR = new EmailFieldValidator() ;

    public static void validateInsertOrUpdate(PersonDTO personDTO) {

        List<String> errors = new ArrayList<>();
        if (personDTO == null) {
            errors.add("personDTO is null");
            throw new IncorrectParameterException(PersonDTO.class.getSimpleName(), errors);
        }
        if (personDTO.getEmail() == null || !EMAIL_VALIDATOR.validate(personDTO.getEmail())) {
            errors.add("Person email has invalid format");
        }

        if (!errors.isEmpty()) {
            LOGGER.error(errors);
            throw new IncorrectParameterException(PersonFieldValidator.class.getSimpleName(), errors);
        }
    }
}
