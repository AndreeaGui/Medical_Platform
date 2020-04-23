package com.assignment4.producer.repositories;

import com.assignment4.producer.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

}
