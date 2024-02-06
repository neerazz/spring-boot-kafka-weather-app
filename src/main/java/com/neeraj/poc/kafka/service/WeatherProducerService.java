package com.neeraj.poc.kafka.service;

import com.neeraj.poc.kafka.converter.WeatherConverter;
import com.neeraj.poc.kafka.model.KafkaProperties;
import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import com.neeraj.poc.kafka.model.pojo.WeatherDTO;
import com.neeraj.poc.kafka.repository.cassandra.WeatherRepository_Cass;
import com.neeraj.poc.kafka.repository.mysql.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherProducerService {

    private final WeatherRepository weatherRepository;
    private final WeatherRepository_Cass weatherRepositoryCass;
    private final KafkaTemplate<String, WeatherDTO> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public boolean processWeatherUpdate(WeatherDTO weatherDTOUpdate) {
        try {
            WeatherEntity weatherEntity = WeatherConverter.getWeatherEntityFromDTO(weatherDTOUpdate);
            weatherRepository.save(weatherEntity);
            return true;
        } catch (Exception exception) {
            log.error("There was an Error Inserting the WeatherDTO to DB.");
        } finally {
            log.info("Sending WeatherDTO Details to Kafka Tower.");
            kafkaTemplate.send(kafkaProperties.getWeatherTopic(), weatherDTOUpdate);
        }
        return false;
    }

    public Page<WeatherDTO> getWeather(Integer limit, Integer pageNumber, String city) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNumber, limit);
            var result = weatherRepository.findByCityContains(pageRequest, city);
            List<WeatherDTO> convertedPojo = WeatherConverter.getWeatherDTO(result.getContent());
            return new PageImpl<>(convertedPojo, pageRequest, result.getTotalElements());
        } catch (Exception exception) {
            log.error("There was an Error while getting the weather from Database.");
            exception.printStackTrace();
            throw new RuntimeException("There was an Error While getting the weather from Database.");
        }
    }

    public Page<WeatherDTO> getWeatherV2(Integer limit, Integer pageNumber, String city, String country, String capital) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNumber, limit);
            List<WeatherEntity_Cass> content = new ArrayList<>();
            if (!city.isBlank() && !country.isBlank() && !capital.isBlank()) {
                var result = weatherRepositoryCass.findByCityAndCountryAndCapital(pageRequest, city, country, capital);
                content = result.getContent();
            } else if (!city.isBlank() && !country.isBlank()) {
                var result = weatherRepositoryCass.findByCityAndCountry(pageRequest, city, country);
                content = result.getContent();
            } else if (!city.isBlank()) {
                var result = weatherRepositoryCass.findByCity(pageRequest, city);
                log.info("Query To get by City : {}. Pagable object: {}", city, result.getPageable());
                content = result.getContent();
            }
            List<WeatherDTO> convertedPojo = WeatherConverter.getWeatherDTOFromCassEntity(content);
            return new PageImpl<>(convertedPojo, pageRequest, content.size());
        } catch (Exception exception) {
            log.error("There was an Error while getting the weather from Database.");
            exception.printStackTrace();
            throw new RuntimeException("There was an Error While getting the weather from Database.");
        }
    }

    public boolean processWeatherUpdateV2(WeatherDTO weatherDTO) {
        try {
            var weatherEntity = WeatherConverter.getWeatherCassEntityFromDTO(weatherDTO);
            weatherRepositoryCass.save(weatherEntity);
            return true;
        } catch (Exception exception) {
            log.error("There was an Error Inserting the WeatherDTO to DB.");
        } finally {
            log.info("Sending WeatherDTO Details to Kafka Tower.");
            kafkaTemplate.send(kafkaProperties.getWeatherTopic(), weatherDTO);
        }
        return false;
    }
}
