package com.epam.learn.resource.domain;

import lombok.Data;

@Data
public class Storage {

    private Integer id;

    private StorageType storageType;

    private String bucket;

    private String path;

}
