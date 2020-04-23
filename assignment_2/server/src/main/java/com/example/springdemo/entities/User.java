package com.example.springdemo.entities;

import com.example.springdemo.entities.enumeration.Gender;
import com.example.springdemo.entities.enumeration.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private static final long serialVersionUID = 6744440378557257174L;

    @Column(name = "username", length = 200)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(final UUID id) {
        super(id);
    }

    @Builder
    public User(UUID id, String username, String password, String name, Gender gender, LocalDate birthDate, Role role) {
        super(id);
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
    }
}
