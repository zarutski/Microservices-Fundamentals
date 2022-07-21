package com.epam.learn.processor.kafka;

import com.epam.learn.processor.service.CommunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerListener {

    private final CommunicationService communicationService;

    public KafkaConsumerListener(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @KafkaListener(topics = "${kafka.topic-upload}", groupId = "${kafka.consumer.group-id}")
    void consumerTopicListener(String message) {
        log.info("Topic listener. Message received: {}", message);
        Integer resourceId = Integer.parseInt(message);
        communicationService.processMessageReceived(resourceId);
    }
}
