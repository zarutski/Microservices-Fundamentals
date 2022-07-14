package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationServiceImpl implements LocationService {

    private ResourceRepository resourceRepository;

    public LocationServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public ResourceLocation save(String location) {
        ResourceLocation resourceLocation = new ResourceLocation();
        resourceLocation.setLocation(location);
        return resourceRepository.save(resourceLocation);
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
    public ResourceLocation verify(Integer id) {
        return resourceRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource doesn't exists - id: " + id));
    }

}
