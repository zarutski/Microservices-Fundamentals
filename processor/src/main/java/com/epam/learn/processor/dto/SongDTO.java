package com.epam.learn.processor.dto;

import com.epam.learn.processor.format.SongLengthSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class SongDTO {

    private String name;
    private String artist;
    private String album;
    @JsonSerialize(using = SongLengthSerializer.class)
    private Long length;
    private Integer resourceId;
    private Integer year;

}
