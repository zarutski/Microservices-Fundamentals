package com.epam.learn.resource.kafka;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.service.LocationService;
import org.springframework.stereotype.Component;

@Component
public class KafkaCommunicationService {

    private final LocationService locationService;

    public KafkaCommunicationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public void processMessageReceived(Integer locationId) {
        ResourceLocation location = locationService.getExisting(locationId);
        locationService.moveToPermanent(location);
    }
}
