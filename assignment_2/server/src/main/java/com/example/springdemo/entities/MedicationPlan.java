package com.example.springdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicationPlan extends BaseEntity {

    private static final long serialVersionUID = -7145939218366552293L;

    @OneToOne
    private Patient patient;

    private LocalDate startTime;
    private LocalDate endTime;

    public MedicationPlan(UUID id) {
        super(id);
    }
}
