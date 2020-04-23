package com.example.springdemo.services;

import com.example.springdemo.SpringDemoApplicationTests;
import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.dto.PersonViewDTO;
import com.example.springdemo.entities.Person;
import com.example.springdemo.errorhandler.IncorrectParameterException;
import com.example.springdemo.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class PersonServiceTest extends SpringDemoApplicationTests {

    @Autowired PersonService personService;


    @Test(expected = IncorrectParameterException.class)
    public void insertDTOBad() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("john.patterson.gmail");
        personService.insert(personDTO);

    }
    @Test
    public void insertDTOGood() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("John Patterson");
        personDTO.setEmail("john.patterson@gmail.com");
        personDTO.setIban("8443 2212 1102 4042");
        personDTO.setAddress("George Baritiu nr. 22");
        Integer id = personService.insert(personDTO);
        PersonViewDTO person2 = personService.findUserById(id);
        assert(!personDTO.equals(person2));

    }
}
