package com.epam.learn.processor.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "resource-service")
public interface ResourceService {

    @GetMapping("/resources/{id}")
    Resource getById(@PathVariable(value = "id") Integer resourceId);
}
