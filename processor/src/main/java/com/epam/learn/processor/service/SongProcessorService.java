package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SongProcessorService implements ProcessorService {

    private static final String NAME_KEY = "dc:title";
    private static final String ARTIST_KEY = "xmpDM:artist";
    private static final String ALBUM_KEY = "xmpDM:album";
    private static final String LENGTH_KEY = "xmpDM:duration";
    private static final String YEAR_KEY = "xmpDM:releaseDate";

    private final Mp3Parser parser;

    public SongProcessorService(Mp3Parser parser) {
        this.parser = parser;
    }

    @Override
    public SongDTO processFile(Resource resource, Integer id) {
        Metadata metadata = extractSongMeta(resource);
        return populateSongMeta(metadata, id);
    }

    private SongDTO populateSongMeta(Metadata metadata, Integer id) {
        SongDTO songDTO = new SongDTO();

        songDTO.setResourceId(id);
        songDTO.setName(metadata.get(NAME_KEY));
        songDTO.setArtist(metadata.get(ARTIST_KEY));
        songDTO.setAlbum(metadata.get(ALBUM_KEY));
        String lengthMeta = metadata.get(LENGTH_KEY);
        songDTO.setLength(((Double) Double.parseDouble(lengthMeta)).longValue());
        String yearMeta = metadata.get(YEAR_KEY);
        songDTO.setYear(Integer.parseInt(yearMeta));

        return songDTO;
    }

    private Metadata extractSongMeta(Resource resource) {
        Metadata metadata = new Metadata();
        try (InputStream inputStream = resource.getInputStream()) {
            parser.parse(inputStream, new DefaultHandler(), metadata, new ParseContext());
        } catch (IOException | SAXException | TikaException ex) {
            throw new RuntimeException("Error parsing song's data", ex);
        }
        return metadata;
    }
}
