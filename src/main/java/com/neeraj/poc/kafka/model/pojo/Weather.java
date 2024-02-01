package com.neeraj.poc.kafka.model.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Weather {

    private double longitude;
    private double latitude;
    private double weatherInCelsius;
    private LocalDateTime timestamp;
    private String city;
    private String description;

}