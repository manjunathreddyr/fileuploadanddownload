package com.javaexplorer.controller;


import com.javaexplorer.entity.FileData;
import com.javaexplorer.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileStorageController {


    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping(path = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
            StringBuilder stringBuilder = new StringBuilder();
        try {
         FileData fileData =  fileStorageService.uploadFile(file);
         stringBuilder.append("File Uploaded successfully")
                 .append(fileData.getFileName());
        }catch (Exception e){
        stringBuilder.append(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());
    }

    @GetMapping(path = "/download/{fileName}")
    public ResponseEntity downloadFile(@PathVariable("fileName") String fileName,@RequestHeader("Content-Type") String contentType){

        byte[] data = fileStorageService.downloadFile(fileName);


        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.valueOf(contentType))
                .body(data);
    }
}
