package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.MedicationPerPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTO;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPerPlan;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;

public class MedicationPlanMapper {

    public static MedicationPlan generateMedicationPlanFromDTO(MedicationPlanDTO dto, Patient patient) {
        return new MedicationPlan(patient, dto.getStartTime(), dto.getEndTime());
    }

    public static MedicationPlanDTO generateMedicationPlanDTOFromEntity(MedicationPlan medicationPlan) {
        return MedicationPlanDTO.builder()
                .id(medicationPlan.getId())
                .patientId(medicationPlan.getPatient().getId())
                .patientName(medicationPlan.getPatient().getName())
                .startTime(medicationPlan.getStartTime())
                .endTime(medicationPlan.getEndTime())
                .build();
    }

    public static MedicationPerPlan generateMedicationPerPlanFromDTO(MedicationPerPlanDTO dto,
                                                                     MedicationPlan medicationPlan,
                                                                     Medication medication) {
        return new MedicationPerPlan(medication, medicationPlan, dto.getDosage(), dto.getIntake());
    }

    public static MedicationPerPlanDTO generateMedicationPerPlanDTOFromEntity(MedicationPerPlan medicationPerPlan) {
        return MedicationPerPlanDTO.builder()
                .id(medicationPerPlan.getId())
                .medicationPlanId(medicationPerPlan.getPlan().getId())
                .medicationId(medicationPerPlan.getMedication().getId())
                .medicationName(medicationPerPlan.getMedication().getName())
                .dosage(medicationPerPlan.getDosage())
                .intake(medicationPerPlan.getIntakeIntervals())
                .build();
    }
}
