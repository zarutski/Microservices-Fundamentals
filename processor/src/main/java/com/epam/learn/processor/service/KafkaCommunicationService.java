package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaCommunicationService implements CommunicationService {

    private final ResourceService resourceService;
    private final SongService songService;

    private final ProcessorService processorService;

    private final RetryTemplate retryTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic-proceeded}")
    private String topicProceeded;

    public KafkaCommunicationService(ResourceService resourceService,
                                     SongService songService,
                                     ProcessorService processorService,
                                     RetryTemplate retryTemplate,
                                     KafkaTemplate<String, String> kafkaTemplate) {
        this.resourceService = resourceService;
        this.songService = songService;
        this.processorService = processorService;
        this.retryTemplate = retryTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void processMessageReceived(Integer resourceId) {
        Resource resource = resourceService.getById(resourceId);
        SongDTO songDTO = retryTemplate.execute(retryContext ->
                processorService.processFile(resource, resourceId));
        retryTemplate.execute(retryContext -> songService.saveSongData(songDTO));
        kafkaTemplate.send(topicProceeded, resourceId.toString());
    }
}
