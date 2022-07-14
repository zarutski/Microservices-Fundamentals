package com.epam.learn.processor.service;

import com.epam.learn.processor.dto.SongDTO;
import lombok.SneakyThrows;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
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

    @SneakyThrows
    @Override
    public SongDTO processFile(MultipartFile multipartFile, Integer id) {
        Metadata metadata = extractSongMeta(multipartFile.getInputStream());
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

    private Metadata extractSongMeta(InputStream inputStream)
            throws TikaException, IOException, SAXException {
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseContext = new ParseContext();
        parser.parse(inputStream, handler, metadata, parseContext);

        inputStream.close();
        return metadata;
    }
}
