package com.epam.learn.resource.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaTopicListener {

    private final KafkaCommunicationService communicationService;

    public KafkaTopicListener(KafkaCommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @KafkaListener(topics = "${kafka.topic-proceeded}", groupId = "${kafka.consumer.group-id}")
    void consumerTopicListener(String message) {
        log.info("Topic listener. Message from processor received: {}", message);
        Integer resourceId = Integer.parseInt(message);
        communicationService.processMessageReceived(resourceId);
    }
}
