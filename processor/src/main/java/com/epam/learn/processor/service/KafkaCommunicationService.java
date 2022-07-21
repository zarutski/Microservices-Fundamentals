package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaCommunicationService implements CommunicationService {

    private final ResourceService resourceService;
    private final SongService songService;

    private final ProcessorService processorService;

    private final RetryTemplate retryTemplate;

    public KafkaCommunicationService(ResourceService resourceService,
                                     SongService songService,
                                     ProcessorService processorService,
                                     RetryTemplate retryTemplate) {
        this.resourceService = resourceService;
        this.songService = songService;
        this.processorService = processorService;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public void processMessageReceived(Integer resourceId) {
        ByteArrayResource resource = resourceService.getById(resourceId);
        SongDTO songDTO = retryTemplate.execute(retryContext ->
                processorService.processFile(resource, resourceId));
        retryTemplate.execute(retryContext -> songService.saveSongData(songDTO));
    }
}
