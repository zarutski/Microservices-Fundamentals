package com.epam.learn.storage.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "storages")
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @NotNull
    private StorageType storageType;
    @Column
    @NotNull
    private String bucket;
    @Column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

}
