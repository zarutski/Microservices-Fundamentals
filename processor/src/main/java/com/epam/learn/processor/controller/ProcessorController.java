package com.epam.learn.processor.controller;

import com.epam.learn.processor.dto.SongDTO;
import com.epam.learn.processor.service.ProcessorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/processor")
public class ProcessorController {

    private final ProcessorService processorService;

    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @PostMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO processFile(@PathVariable(value = "id") Integer id,
                               @RequestParam("file") MultipartFile multipartFile) {
        return processorService.processFile(multipartFile, id);
    }
}
