package com.assignment4.producer.entities;

import com.assignment4.producer.entities.enumeration.Gender;
import com.assignment4.producer.entities.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends User {

    private static final long serialVersionUID = 4156639330441778193L;

    public Patient(final UUID id) {
        super(id);
    }

    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Caregiver caregiver;
    private String medicalRecord;

    @Builder(builderMethodName = "patientBuilder")
    public Patient(UUID id, String username, String password, String name, Gender gender, LocalDate birthDate,
                   Doctor doctor, Caregiver caregiver, String medicalRecord) {
        super(id, username, password, name, gender, birthDate, Role.PATIENT);
        this.doctor = doctor;
        this.caregiver = caregiver;
        this.medicalRecord = medicalRecord;
    }
}
