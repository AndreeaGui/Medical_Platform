package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.MedicationDTO;
import com.example.springdemo.entities.Medication;

public class MedicationMapper {

    public static Medication generateMedicationFromMedicationDTO(MedicationDTO medicationDTO){
        return Medication.builder()
                .name(medicationDTO.getName())
                .sideEffect(medicationDTO.getSideEffect())
                .build();
    }

    public static MedicationDTO generateMEdicationDTOFromMedication(Medication medication){
        return MedicationDTO.builder()
                .id(medication.getId())
                .name(medication.getName())
                .sideEffect(medication.getSideEffect())
                .build();
    }
}
