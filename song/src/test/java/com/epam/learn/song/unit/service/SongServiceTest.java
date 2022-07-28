package com.epam.learn.song.unit.service;

import com.epam.learn.song.domain.Song;
import com.epam.learn.song.repository.SongRepository;
import com.epam.learn.song.service.SongService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @InjectMocks
    SongService songService;

    @Mock
    SongRepository songRepository;

    private static final Integer SONG_ID = 1;

    @Test
    void create() {
        Song song = createSong();
        when(songRepository.save(song)).thenReturn(song);

        Song actual = songService.create(song);
        assertEquals(song, actual);
    }

    @Test
    void lookupByIdExists() {
        Song song = createSong();
        when(songRepository.findById(SONG_ID)).thenReturn(Optional.of(song));

        Optional<Song> actual = songService.lookupById(SONG_ID);
        Optional<Song> expected = Optional.of(song);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIds() {
        Song song = createSong();
        List<Integer> ids = List.of(1, 2, 3);
        when(songRepository.deleteByIdIn(ids)).thenReturn(List.of(song));

        List<Integer> actual = songService.deleteByIds(ids);
        List<Integer> expected = List.of(SONG_ID);
        assertEquals(expected, actual);
    }

    private Song createSong() {
        Song song = new Song();
        song.setId(SONG_ID);
        return song;
    }

}
