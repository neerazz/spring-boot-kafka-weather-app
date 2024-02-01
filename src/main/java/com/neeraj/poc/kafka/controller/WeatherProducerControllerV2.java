package com.neeraj.poc.kafka.controller;

import com.neeraj.poc.kafka.model.pojo.WeatherDTO;
import com.neeraj.poc.kafka.service.WeatherProducerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v2/weather")
@AllArgsConstructor
public class WeatherProducerControllerV2 {

    private final WeatherProducerService weatherProducerService;

    @GetMapping
    public Page<WeatherDTO> getWeather(@RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
                                       @RequestParam(name = "offSet", required = false, defaultValue = "0") Integer offSet, @RequestParam(name = "city", required = false) String city) {
        return weatherProducerService.getWeatherV2(limit, offSet, city);
    }

    @PostMapping("/broadcast")
    public Map<String, String> broadcastWeather(@RequestBody WeatherDTO weatherDTOUpdate) {
        if (weatherProducerService.processWeatherUpdateV2(weatherDTOUpdate)) {
            return Map.of("success", "true", "message", "WeatherDTO update sent to tower!");
        } else {
            return Map.of("success", "true", "message", "There was an error sending to tower!");
        }
    }
}