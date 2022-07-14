package com.epam.learn.resource.controller;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.service.ResourceService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResourceLocation uploadResource(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return resourceService.upload(multipartFile);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> downloadResource(@PathVariable(value = "id") Integer id) throws IOException {
        byte[] resourceBytes = resourceService.download(id).readAllBytes();

        return ResponseEntity.ok()
                .contentLength(resourceBytes.length)
                .body(new ByteArrayResource(resourceBytes));
    }

    @DeleteMapping
    public Map<String, List<Integer>> delete(@RequestParam @Size(min = 1, max = 200) List<Integer> id) {
        return Collections.singletonMap("ids", resourceService.deleteByIds(id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String return400(IllegalArgumentException ex){
        return ex.getMessage();
    }

}
