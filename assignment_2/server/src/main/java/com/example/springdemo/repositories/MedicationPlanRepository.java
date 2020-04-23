package com.example.springdemo.repositories;

import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID>{

    List<MedicationPlan> findAllByPatient(Patient patient);
}
