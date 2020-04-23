package com.example.springdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class MedicationPerPlanDTO {
    private UUID id;
    private UUID medicationPlanId;
    private LocalDate startTime;
    private LocalDate endTime;
    private UUID medicationId;
    private String medicationName;
    private double dosage;
    private String intake;

    @Builder
    public MedicationPerPlanDTO(UUID id, UUID medicationPlanId, UUID medicationId, LocalDate startTime,
                                LocalDate endTime, String medicationName, double dosage, String intake) {
        this.id = id;
        this.medicationPlanId = medicationPlanId;
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.intake = intake;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
