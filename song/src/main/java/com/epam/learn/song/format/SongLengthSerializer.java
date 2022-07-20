package com.epam.learn.song.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SongLengthSerializer extends JsonSerializer<Long> {

    private final String SONG_LENGTH_FORMAT = "%02d:%02d";

    @Override
    public void serialize(Long length, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String lengthFormatted = String.format(SONG_LENGTH_FORMAT, length / 60, length % 60);
        jsonGenerator.writeObject(lengthFormatted);
    }
}
