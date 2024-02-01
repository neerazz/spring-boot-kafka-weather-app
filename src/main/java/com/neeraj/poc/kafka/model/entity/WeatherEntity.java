package com.neeraj.poc.kafka.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_entity")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double longitude;
    private double latitude;
    private String city;
    private String description;
    private double weatherInCelsius;
    private LocalDateTime timestamp;

}