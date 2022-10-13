package com.example.blog.service;

import com.example.blog.dto.ImageDto;
import com.example.blog.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    Category getById(Integer id);

    List<Category> getAll();

    void deleteById(Integer id);

    Category update(Category toModel);

    void saveFileForCategory(Integer id, MultipartFile file);

    void deleteFileForCategory(Integer id);

    ImageDto getFileForCategory(Integer id);
}
