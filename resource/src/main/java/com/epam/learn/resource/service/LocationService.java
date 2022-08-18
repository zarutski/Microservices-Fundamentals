package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;

import java.util.List;

public interface LocationService {
    ResourceLocation saveToStaging(String location);

    List<Integer> deleteByIds(List<Integer> ids);

    ResourceLocation getExisting(Integer id);

    void moveToPermanent(ResourceLocation location);

    List<ResourceLocation> findAllByIds(List<Integer> ids);
}
