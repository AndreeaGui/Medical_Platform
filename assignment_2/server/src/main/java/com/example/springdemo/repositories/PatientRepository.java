package com.example.springdemo.repositories;

import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findByCaregiver(Caregiver caregiver);

    List<Patient> findAllByDoctor(UUID id);
}
