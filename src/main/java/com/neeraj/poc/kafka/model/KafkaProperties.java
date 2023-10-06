package com.neeraj.poc.kafka.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("kafka")
public class KafkaProperties {

    private String bootstrapServer;
    private String schemaRegistryUrl;
    private String weatherTopic;
    private String weatherConsumerGroupId;

}
