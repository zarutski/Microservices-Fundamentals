package com.epam.learn.processor.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final KafkaTopicsProperties topicsProperties;

    @Bean
    public NewTopic resourceProcessed(){
        return TopicBuilder.name(topicsProperties.getProcessed()).build();
    }
}
