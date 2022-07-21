package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.core.io.ByteArrayResource;

public interface ProcessorService {

    SongDTO processFile(ByteArrayResource byteArrayResource, Integer id);
}
