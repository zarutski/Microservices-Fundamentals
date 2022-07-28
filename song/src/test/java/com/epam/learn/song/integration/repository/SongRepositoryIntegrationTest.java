package com.epam.learn.song.integration.repository;

import com.epam.learn.song.domain.Song;
import com.epam.learn.song.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SongRepositoryIntegrationTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    void findByIdSuccess() {
        Song actual = songRepository.findById(1).orElseThrow();
        assertEquals("Nimrod", actual.getAlbum());
    }

    @Test
    void findByIdNotFound() {
        Optional<Song> actual = songRepository.findById(3);
        assertThrows(NoSuchElementException.class, actual::orElseThrow);
    }

    @Test
    void saveSuccess() {
        Song song = createSongStub();
        Song actual = songRepository.save(song);

        assertEquals(song.getName(), actual.getName());
        assertEquals(song.getArtist(), actual.getArtist());
        assertEquals(song.getAlbum(), actual.getAlbum());
        assertEquals(song.getLength(), actual.getLength());
        assertEquals(song.getResourceId(), actual.getResourceId());
        assertEquals(song.getYear(), actual.getYear());
    }

    @Test
    void deleteByIdInSuccess() {
        List<Song> songs = songRepository.deleteByIdIn(List.of(1, 2));
        Integer deletedSongId = songs.get(0).getId();
        assertEquals(1, deletedSongId);
    }

    @Test
    void deleteNoSongsDeleted() {
        List<Song> songs = songRepository.deleteByIdIn(List.of(111, 112, 113));
        assertTrue(songs.isEmpty());
    }

    private Song createSongStub() {
        Song song = new Song();
        song.setName("Hello Johnny");
        song.setArtist("No Name");
        song.setAlbum("Debut");
        song.setLength(207L);
        song.setResourceId(1);
        song.setYear(2000);
        return song;
    }
}
