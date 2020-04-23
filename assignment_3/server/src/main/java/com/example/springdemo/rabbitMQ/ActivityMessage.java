package com.example.springdemo.rabbitMQ;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityMessage {

    private String startTime;
    private String  endTime;
    private String activity;

    public ActivityMessage(@JsonProperty("startTime")String startTime,
                           @JsonProperty("endTime") String endTime,
                           @JsonProperty("activity")String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityMessage{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", activity='" + activity + '\'' +
                '}';
    }
}
