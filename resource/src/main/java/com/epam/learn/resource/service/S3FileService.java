package com.epam.learn.resource.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class S3FileService implements FileService {

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public S3FileService(AmazonS3 amazonS3,
                         @Value("${aws.s3.bucket-name}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        initializeBucket();
    }

    @Override
    public void saveObject(MultipartFile multipartFile) throws IOException {
        String key = multipartFile.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        amazonS3.putObject(bucketName, key, multipartFile.getInputStream(), metadata);
    }

    @Override
    public InputStream getObject(String location) {
        S3Object s3Object = amazonS3.getObject(bucketName, location);
        return s3Object.getObjectContent();
    }

    @Override
    public List<String> deleteObjects(List<String> objects) {
        DeleteObjectsRequest deleteObjectRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(objects.toArray(String[]::new));

        return amazonS3.deleteObjects(deleteObjectRequest)
                .getDeletedObjects().stream()
                .map(DeleteObjectsResult.DeletedObject::getKey).toList();
    }

    private void initializeBucket() {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }
}
