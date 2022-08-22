package com.epam.learn.resource.kafka;

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
    public NewTopic resourceUpload(){
        return TopicBuilder.name(topicsProperties.getUpload()).build();
    }
}
