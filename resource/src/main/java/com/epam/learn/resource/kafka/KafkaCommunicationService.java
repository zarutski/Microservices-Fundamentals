package com.epam.learn.resource.kafka;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaCommunicationService {

    private final LocationService locationService;

    public void processMessageReceived(Integer locationId) {
        ResourceLocation location = locationService.getExisting(locationId);
        locationService.moveToPermanent(location);
    }
}
