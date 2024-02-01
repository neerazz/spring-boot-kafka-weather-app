package com.neeraj.poc.kafka.model.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class WeatherDTO {

    private double longitude;
    private double latitude;
    private double weatherInCelsius;
    private LocalDateTime timestamp;
    private String city;
    private String capital;
    private String country;
    private Double population;
    private String description;

}