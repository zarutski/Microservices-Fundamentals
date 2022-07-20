package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ResourceService {

    ResourceLocation upload(MultipartFile multipartFile) throws IOException;

    InputStream download(Integer id);

    List<Integer> deleteByIds(List<Integer> ids);
}
