package com.example.springdemo.dto;

import com.example.springdemo.entities.enumeration.Gender;
import com.example.springdemo.entities.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientDTO{

    private UUID id;
    private String username;
    private String password;
    private String name;
    private Gender gender;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate;
    private Role role;
    private String medicalRecord;
    private UUID doctorId;
    private String doctorName;
    private UUID caregiverId;
    private String caregiverName;

    @Builder
    public PatientDTO(UUID id, String username, String password, String name, Gender gender, LocalDate birthDate, Role role,
                      String medicalRecord, UUID doctorId, String doctorName, UUID caregiverId, String caregiverName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
        this.medicalRecord = medicalRecord;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.caregiverId = caregiverId;
        this.caregiverName = caregiverName;
    }
}
