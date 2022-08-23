package com.epam.learn.resource.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceProcessedListener {

    private final KafkaCommunicationService communicationService;

    @KafkaListener(topics = "${kafka.topics.processed}", groupId = "${kafka.consumer.group-id}")
    void consumerTopicListener(String message) {
        log.info("Topic listener. Message from processor received: {}", message);
        Integer resourceId = Integer.parseInt(message);
        communicationService.processMessageReceived(resourceId);
    }
}
