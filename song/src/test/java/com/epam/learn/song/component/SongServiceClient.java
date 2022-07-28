package com.epam.learn.song.component;

import com.epam.learn.song.domain.Song;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@TestComponent
@FeignClient(value = "song-service", url = "http://localhost:8093")
public interface SongServiceClient {

    @GetMapping("/songs/{id}")
    ResponseEntity<Song> getSong(@PathVariable("id") Integer id);

    @PostMapping("/songs")
    ResponseEntity<Map<String, Integer>> createSong(@RequestBody Song song);

}
