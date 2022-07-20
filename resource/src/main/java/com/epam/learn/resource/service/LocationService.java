package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;

import java.util.List;

public interface LocationService {
    ResourceLocation save(String location);

    List<Integer> deleteByIds(List<Integer> ids);

    ResourceLocation verify(Integer id);

    List<ResourceLocation> findAllByIds(List<Integer> ids);
}
