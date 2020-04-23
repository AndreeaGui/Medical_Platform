package com.assignment4.producer.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medication")
public class Medication extends BaseEntity{

    private static final long serialVersionUID = 2182459062959447758L;

    @Column(unique = true)
    private String name;
    private String sideEffect;

    public Medication(UUID id) {
        super(id);
    }

    @Builder
    public Medication(UUID id, String name, String sideEffect) {
        super(id);
        this.name = name;
        this.sideEffect = sideEffect;
    }
}
