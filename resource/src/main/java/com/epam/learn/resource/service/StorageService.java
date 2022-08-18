package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.Storage;
import com.epam.learn.resource.domain.StorageType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class StorageService {

    private final StorageServiceClient serviceClient;

    public StorageService(StorageServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @CircuitBreaker(name = "storageBreaker", fallbackMethod = "getStorageStub")
    public Storage getStorageByType(StorageType type) {
        List<Storage> storages = serviceClient.getAll();
        Collections.shuffle(storages);
        return storages.stream()
                .filter(storage -> storage.getStorageType() == type)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No storage found with type: " + type));
    }

    public Storage getStorageStub(StorageType type, Exception exception) {
        log.error("[Calling fallback] Exception while storage fetching: " + exception);
        Storage storage = new Storage();
        storage.setStorageType(type);
        storage.setId(type == StorageType.STAGING ? 1 : 2);
        storage.setBucket("fallback-bucket-" + type);
        return storage;
    }

}
