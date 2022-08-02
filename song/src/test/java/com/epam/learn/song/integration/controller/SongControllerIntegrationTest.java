package com.epam.learn.song.integration.controller;

import com.epam.learn.song.controller.SongController;
import com.epam.learn.song.domain.Song;
import com.epam.learn.song.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SongController.class)
public class SongControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Test
    void createSong() throws Exception {
        Song song = createSongStub();
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(song);
        when(songService.create(song)).thenReturn(song);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isCreated());
    }

    @Test
    void getSongSuccess() throws Exception {
        Song song = createSongStub();
        when(songService.lookupById(1)).thenReturn(Optional.of(song));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/songs/{id}", "1")
        ).andExpect(status().isOk());
    }

    @Test
    void getSongNotFound() throws Exception {
        when(songService.lookupById(1)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/songs/{id}", "1")
        ).andExpect(status().isNotFound());
    }

    @Test
    void deleteSongs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/songs")
                .contentType("application/json")
                .queryParam("id", "1,2,3")
        ).andExpect(status().isOk());
    }

    private Song createSongStub(){
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
