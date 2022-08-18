package com.epam.learn.resource.service;


import com.epam.learn.resource.domain.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "storage-service")
public interface StorageServiceClient {

    @GetMapping("/storages")
    List<Storage> getAll();

}
