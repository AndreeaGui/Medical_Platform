package com.example.springdemo.repositories;

import com.example.springdemo.entities.MedicationPerPlan;
import com.example.springdemo.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicationPerPlanRepository extends JpaRepository<MedicationPerPlan, UUID> {

    List<MedicationPerPlan> findAllByPlan(MedicationPlan plan);
}
