package com.epam.learn.resource.repository;

import com.epam.learn.resource.domain.ResourceLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends CrudRepository<ResourceLocation, Integer> {

    List<ResourceLocation> deleteByIdIn(List<Integer> ids);

    List<ResourceLocation> findAllById(Iterable<Integer> integers);
}
