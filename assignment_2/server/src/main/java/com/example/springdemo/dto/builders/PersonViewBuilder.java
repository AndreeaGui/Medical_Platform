package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.PersonViewDTO;
import com.example.springdemo.entities.Person;

/**
 * example
 */
public class PersonViewBuilder {
    public static PersonViewDTO generateDTOFromEntity(Person person) {
        return new PersonViewDTO(
                person.getId(),
                person.getName(),
                person.getEmail());
    }

    public static Person generateEntityFromDTO(PersonViewDTO personViewDTO) {
        return new Person(
                personViewDTO.getId(),
                personViewDTO.getName(),
                personViewDTO.getEmail());
    }
}
