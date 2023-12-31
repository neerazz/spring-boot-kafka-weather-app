package com.neeraj.poc.kafka.config;

import com.neeraj.poc.kafka.model.KafkaProperties;
import com.neeraj.poc.kafka.model.pojo.Weather;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumerConfig {

    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, Weather> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getWeatherConsumerGroupId());
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Weather> userConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Weather> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        return containerFactory;
    }
}
