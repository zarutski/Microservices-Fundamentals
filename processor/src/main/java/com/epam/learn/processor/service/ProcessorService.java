package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.core.io.Resource;

public interface ProcessorService {

    SongDTO processFile(Resource resource, Integer id);
}
