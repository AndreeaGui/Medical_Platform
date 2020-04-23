package com.example.springdemo.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseEvent {

    private static final String type = "ACTIVITY_PROBLEM";
    private final Activity activity;
}
