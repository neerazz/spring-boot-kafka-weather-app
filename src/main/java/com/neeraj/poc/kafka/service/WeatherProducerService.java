package com.neeraj.poc.kafka.service;

import com.neeraj.poc.kafka.model.KafkaProperties;
import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.pojo.Weather;
import com.neeraj.poc.kafka.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherProducerService {

    private final WeatherRepository weatherRepository;
    private final KafkaTemplate<String, Weather> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public boolean processWeatherUpdate(Weather weatherUpdate) {
        try {
            var weatherEntity = WeatherEntity.builder()
                    .longitude(weatherUpdate.getLongitude())
                    .latitude(weatherUpdate.getLatitude())
                    .timestamp(weatherUpdate.getTimestamp())
                    .weatherInCelsius(weatherUpdate.getWeatherInCelsius())
                    .build();
            weatherRepository.save(weatherEntity);
            return true;
        } catch (Exception exception) {
            log.error("There was an Error Inserting the Weather to DB.");
        } finally {
            log.info("Sending Weather Details to Kafka Tower.");
            kafkaTemplate.send(kafkaProperties.getWeatherTopic(), weatherUpdate);
        }
        return false;
    }
}
