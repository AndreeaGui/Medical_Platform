package com.assignment4.producer.entities;


import com.assignment4.producer.entities.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends User{

    public static final long serialVersionUID = -5861548743826789348L;

    public Doctor(UUID id) {
        super(id);
    }

    @Builder(builderMethodName = "doctorFromUserBuilder")
    public Doctor(User user) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getName(),
                user.getGender(), user.getBirthDate(), Role.DOCTOR);
    }
}
