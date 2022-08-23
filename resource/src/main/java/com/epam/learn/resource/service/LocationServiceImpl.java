package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.domain.Storage;
import com.epam.learn.resource.domain.StorageType;
import com.epam.learn.resource.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationServiceImpl implements LocationService {

    private ResourceRepository resourceRepository;

    private StorageService storageService;

    public LocationServiceImpl(ResourceRepository resourceRepository,
                               StorageService storageService) {
        this.resourceRepository = resourceRepository;
        this.storageService = storageService;
    }

    @Override
    public ResourceLocation saveToStaging(String location) {
        Storage storage = storageService.getStorageByType(StorageType.STAGING);
        ResourceLocation resourceLocation = new ResourceLocation();
        resourceLocation.setLocation(location);
        resourceLocation.setStorageId(storage.getId());
        return resourceRepository.save(resourceLocation);
    }

    @Override
    public void moveToPermanent(ResourceLocation location) {
        Storage storage = storageService.getStorageByType(StorageType.PERMANENT);
        location.setStorageId(storage.getId());
        resourceRepository.save(location);
    }

    @Override
    public List<ResourceLocation> findAllByIds(List<Integer> ids) {
        return resourceRepository.findAllById(ids);
    }

    @Override
    public List<Integer> deleteByIds(List<Integer> id) {
        return resourceRepository.deleteByIdIn(id).stream().map(ResourceLocation::getId).toList();
    }

    @Override
    public ResourceLocation getExisting(Integer id) {
        return resourceRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource doesn't exists - id: " + id));
    }

}
