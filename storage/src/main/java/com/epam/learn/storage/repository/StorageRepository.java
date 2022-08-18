package com.epam.learn.storage.repository;

import com.epam.learn.storage.domain.Storage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StorageRepository extends CrudRepository<Storage, Integer> {

    List<Storage> deleteByIdIn(List<Integer> ids);

    @Override
    List<Storage> findAll();

}
