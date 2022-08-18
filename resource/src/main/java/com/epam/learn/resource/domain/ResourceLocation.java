package com.epam.learn.resource.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "resource_locations")
@NoArgsConstructor
public class ResourceLocation {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @JsonIgnore
    private String location;
    @Column
    private Integer storageId;

}
