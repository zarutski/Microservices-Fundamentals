package com.epam.learn.resource.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    void saveObject(MultipartFile multipartFile) throws IOException;

    InputStream getObject(String location);

    List<String> deleteObjects(List<String> objects);
}
