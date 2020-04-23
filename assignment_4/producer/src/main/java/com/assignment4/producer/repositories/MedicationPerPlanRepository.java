package com.assignment4.producer.repositories;

import com.assignment4.producer.entities.MedicationPerPlan;
import com.assignment4.producer.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicationPerPlanRepository extends JpaRepository<MedicationPerPlan, UUID> {

    List<MedicationPerPlan> findAllByPlan(MedicationPlan plan);
    List<MedicationPerPlan> findAll();
}
