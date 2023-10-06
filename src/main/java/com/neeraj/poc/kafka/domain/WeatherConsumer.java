package com.neeraj.poc.kafka.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherConsumer {

    @KafkaListener(topics = "${kafka.weatherConsumer}", groupId = "${kafka.weatherConsumerGroupId}")
    public void relayWeatherUpdate(String weatherUpdate) {
        log.info("Weather update received: {}", weatherUpdate);
    }
}