package com.epam.learn.storage.service;

import com.epam.learn.storage.domain.Storage;
import com.epam.learn.storage.repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Storage create(Storage storage) {
        log.info("Storage input while creation: " + storage);
        return storageRepository.save(storage);
    }

    public Optional<Storage> lookupById(Integer id) {
        return storageRepository.findById(id);
    }

    public List<Storage> findAll() {
        log.info("Loading available storages");
        return storageRepository.findAll();
    }

    public List<Integer> deleteByIds(List<Integer> ids) {
        return storageRepository.deleteByIdIn(ids).stream().map(Storage::getId).toList();
    }
}
