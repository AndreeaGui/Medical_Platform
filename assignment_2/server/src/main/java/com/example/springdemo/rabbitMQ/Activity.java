package com.example.springdemo.rabbitMQ;


import com.example.springdemo.entities.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseEntity {

    private Long starTime;
    private Long endTime;
    private String activity;
    private UUID personId;
}
