package com.assignment4.producer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicationPerPlan extends BaseEntity{
    private static final long serialVersionUID = -8793602099750749611L;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="medication_id")
    private Medication medication;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="plan_id")
    private MedicationPlan plan;
    private double dosage;
    private String intakeIntervals;
}
