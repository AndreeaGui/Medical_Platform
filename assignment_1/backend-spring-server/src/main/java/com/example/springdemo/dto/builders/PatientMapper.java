package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.PatientDTO;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Doctor;
import com.example.springdemo.entities.Patient;

public class PatientMapper {

    public static PatientDTO generatePatientDTOFromPatient(Patient user){
        return PatientDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .medicalRecord(user.getMedicalRecord())
                .doctorId(user.getDoctor().getId())
                .doctorName(user.getDoctor().getName())
                .caregiverId(user.getCaregiver().getId())
                .caregiverName(user.getCaregiver().getName())
                .build();
    }

    public static Patient generatePatientFromPatientDTO(PatientDTO user, Doctor doctor, Caregiver caregiver){
        return Patient.patientBuilder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .medicalRecord(user.getMedicalRecord())
                .doctor(doctor)
                .caregiver(caregiver)
                .build();
    }

}
