package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessorService {

    SongDTO processFile(MultipartFile multipartFile, Integer id);
}
