package com.epam.learn.song.component;

import com.epam.learn.song.domain.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Map;

@Configurable
public class SongDataTableType {

    private final ObjectMapper objectMapper;

    public SongDataTableType(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @DataTableType
    public Song songTypeConverter(Map<String, String> tableRow) {
        return objectMapper.convertValue(tableRow, Song.class);
    }

}
