package com.epam.learn.processor.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic-proceeded}")
    private String topicProceeded;

    @Bean
    public NewTopic resourceProceeded(){
        return TopicBuilder.name(topicProceeded).build();
    }
}
