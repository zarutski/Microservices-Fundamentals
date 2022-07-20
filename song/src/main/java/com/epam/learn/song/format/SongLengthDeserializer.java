package com.epam.learn.song.format;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SongLengthDeserializer extends JsonDeserializer<Long> {
    private static final String LENGTH_DELIMITER = ":";
    private static final int INPUT_PARTS_NUMBER = 2;
    private static final long SECONDS_MAX_VAL = 59;
    private static final long SECONDS_MULTIPLIER = 60;

    @Override
    public Long deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext) throws IOException {
        String inputValue = jsonParser.getValueAsString();
        String[] inputParts = inputValue.split(LENGTH_DELIMITER);

        long minutes = Long.parseLong(inputParts[0]);
        long seconds = Long.parseLong(inputParts[1]);

        verifyInput(inputParts, minutes, seconds);
        return minutes * SECONDS_MULTIPLIER + seconds;
    }

    private void verifyInput(String[] inputParts, long minutes, long seconds) {
        if (inputParts.length != INPUT_PARTS_NUMBER
                || minutes < 0
                || seconds < 0
                || seconds > SECONDS_MAX_VAL) {
            throw new IllegalArgumentException("Invalid song's length format");
        }
    }
}
