package com.neeraj.poc.kafka.controller;

import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.pojo.Weather;
import com.neeraj.poc.kafka.service.WeatherProducerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherProducerController {

    private final WeatherProducerService weatherProducerService;

    @GetMapping
    public Page<Weather> getWeather(@RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
                                    @RequestParam(name = "offSet", required = false, defaultValue = "0") Integer offSet, @RequestParam(name = "city", required = false) String city) {
        return weatherProducerService.getWeather(limit, offSet, city);
    }

    @PostMapping("/broadcast")
    public Map<String, String> broadcastWeather(@RequestBody Weather weatherUpdate) {
        if (weatherProducerService.processWeatherUpdate(weatherUpdate)) {
            return Map.of("success", "true", "message", "Weather update sent to tower!");
        } else {
            return Map.of("success", "true", "message", "There was an error sending to tower!");
        }
    }
}