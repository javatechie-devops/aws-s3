package com.javatechie.controller;

import com.javatechie.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class StorageController {

    @Autowired
    private  StorageService service;


    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        service.uploadFile(file.getOriginalFilename(),file);
        return "File uploaded";
    }



    @GetMapping("/view/{fileName}")
    public ResponseEntity<InputStreamResource> viewFile(@PathVariable String fileName) {
       var s3Object= service.getFile(fileName);
       var content= s3Object.getObjectContent();
       //check the extension of file or content
        //dynamically build the mediaType and set in response
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\""+fileName+"\"")
                .body(new InputStreamResource(content));

    }
    //image , PDF , CSV , XML , JSON , EXCEL , HTML

    // delete the content from bucket
    //download the content from bucket
}
