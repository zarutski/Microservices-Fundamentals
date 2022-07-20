package com.epam.learn.song.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SongConverter implements Converter<Song, Integer> {

    @Override
    public Integer convert(Song source) {
        return source.getId();
    }
}
