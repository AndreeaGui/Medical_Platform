package com.example.springdemo.repositories;

import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    List<MedicationPlan> findAllByPatient(Patient patient);

    List<MedicationPlan> findAllByPatientAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Patient patient,
                                                                                             LocalDate startDate,
                                                                                             LocalDate endDate);

}
