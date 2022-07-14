package com.epam.learn.song.controller;

import com.epam.learn.song.domain.Song;
import com.epam.learn.song.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping(path = "/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Integer> createSong(@RequestBody Song song) {
        Song created = songService.create(song);
        return Collections.singletonMap("id", created.getId());
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable("id") Integer id) {
        return songService.lookupById(id)
                .orElseThrow(() -> new NoSuchElementException("Song doesn't exists: " + id));
    }

    @DeleteMapping
    public Map<String, List<Integer>> delete(@RequestParam @Size(min = 1, max = 200) List<Integer> id) {
        return Collections.singletonMap("ids", songService.deleteByIds(id));
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
