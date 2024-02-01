package com.neeraj.poc.kafka.model.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherEntity_Cass {

    @PrimaryKey
    private String id;

    private double longitude;
    private double latitude;
    private String city;
    private String capital;
    private String country;
    private Double population;
    private String description;
    private double weatherInCelsius;
    private LocalDateTime timestamp;

}