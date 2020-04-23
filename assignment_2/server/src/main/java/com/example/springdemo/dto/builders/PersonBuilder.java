package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.entities.Person;
import lombok.NoArgsConstructor;

/**
 * example
 */
@NoArgsConstructor
public class PersonBuilder {

    public static PersonDTO generateDTOFromEntity(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getEmail(),
                person.getIban(),
                person.getAddress());
    }

    public static Person generateEntityFromDTO(PersonDTO personDTO) {
        return new Person(
                personDTO.getId(),
                personDTO.getName(),
                personDTO.getEmail(),
                personDTO.getIban(),
                personDTO.getAddress());
    }
}
