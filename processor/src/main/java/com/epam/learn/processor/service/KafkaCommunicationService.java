package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import com.epam.learn.processor.kafka.KafkaTopicsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaCommunicationService implements CommunicationService {

    private final ResourceService resourceService;
    private final SongService songService;

    private final ProcessorService processorService;

    private final RetryTemplate retryTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTopicsProperties topicsProperties;

    @Override
    public void processMessageReceived(Integer resourceId) {
        Resource resource = resourceService.getById(resourceId);
        SongDTO songDTO = retryTemplate.execute(retryContext ->
                processorService.processFile(resource, resourceId));
        retryTemplate.execute(retryContext -> songService.saveSongData(songDTO));
        kafkaTemplate.send(topicsProperties.getProcessed(), resourceId.toString());
    }
}
