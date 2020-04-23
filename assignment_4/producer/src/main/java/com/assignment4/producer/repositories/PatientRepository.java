package com.assignment4.producer.repositories;

import com.assignment4.producer.entities.Caregiver;
import com.assignment4.producer.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findByCaregiver(Caregiver caregiver);

    List<Patient> findAllByDoctor(UUID id);
}
