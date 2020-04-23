package com.assignment4.producer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TakenMedication extends BaseEntity {
    private static final long serialVersionUID = 3228708300676333191L;

    @OneToOne
    private Medication medication;
    @OneToOne
    private Patient patient;
    private boolean taken;
    private ZonedDateTime intakeTime;

    public TakenMedication(UUID id){
        super(id);
    }

}
