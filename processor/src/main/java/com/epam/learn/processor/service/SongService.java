package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "song-service", url = "${song-service-app.url}")
public interface SongService {

    @PostMapping("/songs")
    Map<String, Integer> saveSongData(@RequestBody SongDTO song);
}
