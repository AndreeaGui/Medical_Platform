package com.example.springdemo.entities;

import com.example.springdemo.entities.enumeration.Gender;
import com.example.springdemo.entities.enumeration.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "caregiver")
public class Caregiver extends User {

    public static final long serialVersionUID = 3848876660350418458L;

    public Caregiver(UUID id) {
        super(id);
    }

    @Builder(builderMethodName = "caregiverFromUserBuilder")
    public Caregiver(User user) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getName(),
                user.getGender(), user.getBirthDate(), Role.CAREGIVER);
    }

    /*@Builder(builderMethodName = "caregiverFromFields")
    public Caregiver(UUID id, String username, String password, String name, Gender gender,
                     LocalDate birthDate, Role role) {
        super(id, username, password, name, gender, birthDate, Role.CAREGIVER);
    }*/
}
