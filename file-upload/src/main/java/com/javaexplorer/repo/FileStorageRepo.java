package com.javaexplorer.repo;

import com.javaexplorer.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepo extends JpaRepository<FileData,Long> {

   FileData  findByFileName(String  fileName);
}
