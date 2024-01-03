package com.neeraj.poc.kafka.controller;

import com.neeraj.poc.kafka.model.pojo.Weather;
import com.neeraj.poc.kafka.service.WeatherProducerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class WeatherProducerController {

    private final WeatherProducerService weatherProducerService;

    @PostMapping("/broadcastWeather")
    public Map<String, String> broadcastWeather(@RequestBody Weather weatherUpdate) {
        if (weatherProducerService.processWeatherUpdate(weatherUpdate)) {
            return Map.of("success", "true", "message", "Weather update sent to tower!");
        } else {
            return Map.of("success", "true", "message", "There was an error sending to tower!");
        }
    }
}