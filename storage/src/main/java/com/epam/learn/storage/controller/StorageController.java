package com.epam.learn.storage.controller;

import com.epam.learn.storage.domain.Storage;
import com.epam.learn.storage.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Validated
@RestController
@RequestMapping(path = "/storages")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Integer> createStorage(@RequestBody Storage storage) {
        Storage created = storageService.create(storage);
        return Collections.singletonMap("id", created.getId());
    }

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable("id") Integer id) {
        return storageService.lookupById(id)
                .orElseThrow(() -> new NoSuchElementException("Storage doesn't exists: " + id));
    }

    @GetMapping
    public List<Storage> getStorages() {
        return storageService.findAll();
    }

    @DeleteMapping
    public Map<String, List<Integer>> delete(@RequestParam @Size(min = 1, max = 200) List<Integer> id) {
        return Collections.singletonMap("ids", storageService.deleteByIds(id));
    }
}
