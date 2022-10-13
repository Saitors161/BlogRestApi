package com.example.blog.service;

import com.example.blog.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFileForEntity(String entityName, Integer entityId, MultipartFile multipartFile);
    void deleteFileForEntity(String imagePath);
    ImageDto getByteArrayFromStorageFile(String filePath);
}
