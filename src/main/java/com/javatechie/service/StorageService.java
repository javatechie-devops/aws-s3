package com.javatechie.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class StorageService {

    public static final String BUCKET = "javatechie-bucket";
    @Autowired
    private AmazonS3 amazonS3;

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        amazonS3.putObject(BUCKET, keyName, file.getInputStream(), null);
        log.info("file {} uploaded successfully ", keyName);

    }

    public S3Object getFile(String keyName) {
        return amazonS3.getObject(BUCKET, keyName);
    }

}
