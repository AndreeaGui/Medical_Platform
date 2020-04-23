package com.example.springdemo.services;

import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.dto.PersonViewDTO;
import com.example.springdemo.dto.builders.PersonBuilder;
import com.example.springdemo.dto.builders.PersonViewBuilder;
import com.example.springdemo.entities.Person;
import com.example.springdemo.errorhandler.EntityValidationException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.PersonRepository;
import com.example.springdemo.validators.PersonFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * example
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonViewDTO findUserById(Integer id){
        Optional<Person> person  = personRepository.findById(id);

        if (!person.isPresent()) {
            throw new ResourceNotFoundException("Person", "user id", id);
        }
        return PersonViewBuilder.generateDTOFromEntity(person.get());
    }

    public List<PersonViewDTO> findAll(){
        List<Person> persons = personRepository.getAllOrdered();

        return persons.stream()
                .map(PersonViewBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public Integer insert(PersonDTO personDTO) {

        PersonFieldValidator.validateInsertOrUpdate(personDTO);

        return personRepository
                .save(PersonBuilder.generateEntityFromDTO(personDTO))
                .getId();
    }

    public Integer update(PersonDTO personDTO) {

        Optional<Person> person = personRepository.findById(personDTO.getId());

        if(!person.isPresent()){
            throw new ResourceNotFoundException("Person", "user id", personDTO.getId().toString());
        }
        PersonFieldValidator.validateInsertOrUpdate(personDTO);

        return personRepository.save(PersonBuilder.generateEntityFromDTO(personDTO)).getId();
    }

    public void delete(PersonViewDTO personViewDTO){
        this.personRepository.deleteById(personViewDTO.getId());
    }

}
