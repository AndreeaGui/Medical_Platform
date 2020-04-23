package com.example.springdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class MedicationPlanDTO {

    private UUID id;
    private String patientName;
    private UUID patientId;
    private @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startTime;
    private @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endTime;

    @Builder
    public MedicationPlanDTO(UUID id, String patientName, UUID patientId, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
