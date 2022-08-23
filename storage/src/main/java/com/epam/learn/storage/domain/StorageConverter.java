package com.epam.learn.storage.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageConverter implements Converter<Storage, Integer> {

    @Override
    public Integer convert(Storage storage) {
        return storage.getId();
    }
}
