package com.example.springdemo.dto;

import com.example.springdemo.entities.enumeration.Gender;
import com.example.springdemo.entities.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String username;
    private String password;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private Role role;

    @Builder
    public UserDTO(UUID id, String username, String password, String name, Gender gender, LocalDate birthDate, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
    }
}
