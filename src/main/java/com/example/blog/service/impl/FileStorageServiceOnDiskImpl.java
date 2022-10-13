package com.example.blog.service.impl;

import com.example.blog.dto.ImageDto;
import com.example.blog.exception.FailedToSaveFileException;
import com.example.blog.exception.FileInBlogAppNotFoundException;
import com.example.blog.service.FileStorageService;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceOnDiskImpl implements FileStorageService {
    @Value("${fileStorage}")
    private String pathToFileStorage;

    @Override
    public String saveFileForEntity(String entityName, Integer entityId, MultipartFile multipartFile) {
        String absolutePathToDirectories =  pathToFileStorage + File.separator
                + entityName + File.separator
                + getPathForDirectoriesById(entityId);
        Path pathDirectories = null;
        try {
            pathDirectories = Files.createDirectories(Paths.get(absolutePathToDirectories));
        } catch (FileAlreadyExistsException ignored) {
            pathDirectories = Paths.get(absolutePathToDirectories);
        } catch (IOException e) {
            throw new FailedToSaveFileException("Failed to save file with name " + multipartFile.getName() + "Directories didnt create" + e.getMessage());
        }
        if (pathDirectories != null) {
            try {
                multipartFile.transferTo(new File(pathDirectories  + File.separator + multipartFile.getOriginalFilename()));
            } catch (IOException e) {
                throw new FailedToSaveFileException("Failed to save file with name " + multipartFile.getName() + e.getMessage());
            }
            return pathDirectories  + File.separator + multipartFile.getOriginalFilename();
        }
        throw new FailedToSaveFileException("Failed to save file with name " + multipartFile.getName());
    }

    @Override
    public void deleteFileForEntity(String imagePath) {
        try {
            Files.deleteIfExists(Paths.get(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageDto getByteArrayFromStorageFile(String filePath) {

        try {
            File file = new File(filePath);
            return new ImageDto(file.getName(),new String(Base64.encodeBase64(Base64.encodeBase64(FileUtils.readFileToByteArray(file))), "UTF-8"));
        } catch (IOException | NullPointerException e) {
            throw new FileInBlogAppNotFoundException("File for this entity not found");
        }
    }

    private String getPathForDirectoriesById(Integer id) {
        String idString = String.format("%010d", id);
        int lengthAfterGeneratePath = 14;
        int indexOriginalString = 0;
        StringBuilder builder = new StringBuilder(idString);
        int currentCounterBuilder = 0;
        while (builder.length() < lengthAfterGeneratePath) {
            currentCounterBuilder++;
            indexOriginalString++;
            if (indexOriginalString % 2 == 0) {
                builder.insert(currentCounterBuilder, File.separator);
                currentCounterBuilder++;
            }
        }
        System.out.println("File path in storage = " + File.separator + builder );
        return File.separator + builder;
    }
}
