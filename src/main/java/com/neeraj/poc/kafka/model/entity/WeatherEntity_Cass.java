package com.neeraj.poc.kafka.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherEntity_Cass {

    @PrimaryKeyColumn(
            name = "id",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String id;

    private double longitude;
    private double latitude;
    @PrimaryKeyColumn(name = "city", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String city;
    @PrimaryKeyColumn(name = "capital", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String capital;
    @PrimaryKeyColumn(name = "country", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String country;
    private Double population;
    private String description;
    private double weatherInCelsius;
    private LocalDateTime timestamp;

}