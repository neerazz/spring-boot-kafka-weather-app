package com.neeraj.poc.kafka.service;

import com.neeraj.poc.kafka.domain.WeatherConverter;
import com.neeraj.poc.kafka.model.KafkaProperties;
import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.pojo.Weather;
import com.neeraj.poc.kafka.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherProducerService {

    private final WeatherRepository weatherRepository;
    private final KafkaTemplate<String, Weather> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public boolean processWeatherUpdate(Weather weatherUpdate) {
        try {
            WeatherEntity weatherEntity = WeatherConverter.getWeatherEntity(weatherUpdate);
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

    public Page<Weather> getWeather(Integer limit, Integer pageNumber, String city) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNumber, limit);
            var result = weatherRepository.findByCityContains(pageRequest, city);
            List<Weather> convertedPojo = WeatherConverter.getWeather(result.getContent());
            return new PageImpl<>(convertedPojo, pageRequest, result.getTotalElements());
        } catch (Exception exception) {
            log.error("There was an Error while getting the weather from Database.");
            exception.printStackTrace();
            throw new RuntimeException("There was an Error While getting the weather from Database.");
        }
    }
}
