package com.epam.learn.resource.service;

import com.epam.learn.resource.domain.ResourceLocation;
import com.epam.learn.resource.kafka.KafkaTopicsProperties;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class S3ResourceService implements ResourceService {

    private final LocationService locationService;

    private final FileService fileService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTopicsProperties topicsProperties;

    @Override
    public ResourceLocation upload(MultipartFile multipartFile) throws IOException {
        verifyFileType(multipartFile);
        fileService.saveObject(multipartFile);
        String filename = multipartFile.getOriginalFilename();
        ResourceLocation location = locationService.saveToStaging(filename);
        kafkaTemplate.send(topicsProperties.getUpload(), location.getId().toString());
        return location;
    }

    @Override
    public InputStream download(Integer id) {
        ResourceLocation resourceLocation = locationService.getExisting(id);
        String location = resourceLocation.getLocation();
        return fileService.getObject(location);
    }

    @Override
    public List<Integer> deleteByIds(List<Integer> ids) {
        List<ResourceLocation> foundResources = locationService.findAllByIds(ids);
        if (foundResources.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> locations = foundResources.stream().map(ResourceLocation::getLocation).toList();
        List<String> deleted = fileService.deleteObjects(locations);
        List<Integer> idsToDelete = getIntersectionIds(foundResources, deleted);
        return locationService.deleteByIds(idsToDelete);
    }

    private void verifyFileType(MultipartFile multipartFile) throws IOException {
        Tika tika = new Tika();
        String mimeType = tika.detect(multipartFile.getInputStream());
        if (!"audio/mpeg".equals(mimeType)) {
            throw new IllegalArgumentException("Input file type is not valid");
        }
    }

    private List<Integer> getIntersectionIds(List<ResourceLocation> locations,
                                             List<String> locationNames) {
        return locations.stream()
                .filter(location -> locationNames.contains(location.getLocation()))
                .map(ResourceLocation::getId).toList();
    }
}
