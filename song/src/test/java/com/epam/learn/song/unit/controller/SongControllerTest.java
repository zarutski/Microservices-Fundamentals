package com.epam.learn.song.unit.controller;

import com.epam.learn.song.controller.SongController;
import com.epam.learn.song.domain.Song;
import com.epam.learn.song.service.SongService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongControllerTest {

    @InjectMocks
    SongController songController;

    @Mock
    SongService songService;

    private static final Integer SONG_ID = 1;

    @Test
    void saveSong() {
        Song song = createSong();
        when(songService.create(song)).thenReturn(song);

        Map<String, Integer> expected = Collections.singletonMap("id", song.getId());
        Map<String, Integer> response = songController.createSong(song);
        assertEquals(expected, response);
    }

    @Test
    void getSongSuccess() {
        Song song = createSong();
        when(songService.lookupById(SONG_ID)).thenReturn(Optional.of(song));

        Song response = songController.getSong(SONG_ID);
        assertEquals(song, response);
    }

    @Test
    void getSongNotFound() {
        when(songService.lookupById(SONG_ID)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> songController.getSong(SONG_ID));
    }

    @Test
    void deleteSongs() {
        List<Integer> ids = List.of(1, 2, 3);
        List<Integer> deleted = List.of(SONG_ID);
        when(songService.deleteByIds(ids)).thenReturn(deleted);

        Map<String, List<Integer>> expected = Collections.singletonMap("ids", deleted);
        Map<String, List<Integer>> response = songController.delete(ids);
        assertEquals(expected, response);
    }

    private Song createSong() {
        Song song = new Song();
        song.setId(SONG_ID);
        return song;
    }

}
