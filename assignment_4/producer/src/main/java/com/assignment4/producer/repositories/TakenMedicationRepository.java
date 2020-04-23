package com.assignment4.producer.repositories;


import com.assignment4.producer.entities.Patient;
import com.assignment4.producer.entities.TakenMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TakenMedicationRepository extends JpaRepository<TakenMedication, UUID> {

    List<TakenMedication> findByPatient(Patient patient);
}
