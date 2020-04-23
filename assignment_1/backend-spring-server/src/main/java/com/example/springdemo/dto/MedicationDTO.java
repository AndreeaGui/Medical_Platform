package com.example.springdemo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MedicationDTO {

    private UUID id;
    private String name;
    private String sideEffect;

    @Builder
    public MedicationDTO(UUID id, String name, String sideEffect) {
        this.id = id;
        this.name = name;
        this.sideEffect = sideEffect;
    }
}
