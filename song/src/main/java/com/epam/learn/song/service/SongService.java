package com.epam.learn.song.service;

import com.epam.learn.song.domain.Song;
import com.epam.learn.song.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song create(Song song) {
        log.info("Song's data input while creation: " + song);
        return songRepository.save(song);
    }

    public Optional<Song> lookupById(Integer id) {
        return songRepository.findById(id);
    }

    public List<Integer> deleteByIds(List<Integer> ids) {
        return songRepository.deleteByIdIn(ids).stream().map(Song::getId).toList();
    }

}
