package com.neeraj.poc.kafka.domain;

import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.pojo.Weather;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class WeatherConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public static List<Weather> getWeather(List<WeatherEntity> weatherEntities) {
        if (weatherEntities == null) {
            return new ArrayList<>();
        } else {
            return weatherEntities.stream()
                    .map(entity -> mapper.map(entity, Weather.class))
                    .toList();
        }
    }

    public static WeatherEntity getWeatherEntity(Weather weather) {
//        return WeatherEntity.builder()
//                .longitude(weather.getLongitude())
//                .latitude(weather.getLatitude())
//                .timestamp(weather.getTimestamp())
//                .description(weather.getDescription())
//                .city(weather.getCity())
//                .weatherInCelsius(weather.getWeatherInCelsius())
//                .build();
        return mapper.map(weather, WeatherEntity.class);
    }
}
