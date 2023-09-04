package com.javaexplorer.service;

import com.javaexplorer.entity.FileData;
import com.javaexplorer.repo.FileStorageRepo;
import com.javaexplorer.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {


    @Autowired
    private FileStorageRepo repo;


    public FileData uploadFile(MultipartFile file) throws IOException {

        FileData fileData = FileData.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(FileUtil.compressFile(file.getBytes()))
                .build();
        return  repo.save(fileData);
    }

    public  byte[] downloadFile(String fileName){
       FileData fileData =  repo.findByFileName(fileName);
       return  FileUtil.decompressFile(fileData.getData());
    }
}
