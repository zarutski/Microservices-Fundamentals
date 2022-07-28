package com.epam.learn.song.domain;

import com.epam.learn.song.format.SongLengthDeserializer;
import com.epam.learn.song.format.SongLengthSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "songs")
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column
    private String name;
    @Column
    private String artist;
    @Column
    private String album;
    @Column
    @JsonSerialize(using = SongLengthSerializer.class)
    @JsonDeserialize(using = SongLengthDeserializer.class)
    private Long length;
    @Column
    private Integer resourceId;
    @Column(name="release_year")
    private Integer year;

}
