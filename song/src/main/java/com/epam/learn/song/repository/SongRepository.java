package com.epam.learn.song.repository;

import com.epam.learn.song.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {

    List<Song> deleteByIdIn(List<Integer> ids);
}
