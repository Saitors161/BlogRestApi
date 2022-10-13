package com.example.blog.service.impl;

import com.example.blog.dto.ImageDto;
import com.example.blog.exception.EntityInBlogCannotBeCreatedException;
import com.example.blog.exception.EntityInBlogNotFoundException;
import com.example.blog.model.Category;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;
import com.example.blog.service.FileStorageService;
import com.example.blog.util.enums.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final FileStorageService storageService;

    private final String currentNameEntity = EntityType.CATEGORY.toString();

    @Override
    public Category save(Category category) {
        checkDataCategoryBeforeSave(category);
        return categoryRepository.save(category);
    }

    private void checkDataCategoryBeforeSave(Category category) {
        if (category.getHeader() == null || category.getHeader().equals("")) {
            throw new EntityInBlogCannotBeCreatedException("Article with empty header cannot be created");
        }
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> (new EntityInBlogNotFoundException("Category with id " + id + " not found")));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            return categoryRepository.save(category);
        }
        throw new EntityInBlogNotFoundException("Category with id " + category.getId() + " not found");
    }

    @Override
    public void saveFileForCategory(Integer id, MultipartFile file) {
        Category category = getById(id);
        if (category.getImagePath() != null && !category.getImagePath().equals("")) {
            storageService.deleteFileForEntity(category.getImagePath());
        }
        category.setImagePath(storageService.saveFileForEntity(currentNameEntity, id, file));
        categoryRepository.save(category);

    }

    @Override
    public void deleteFileForCategory(Integer id) {
        Category category = getById(id);
        if (category.getImagePath() != null && !category.getImagePath().equals("")) {
            storageService.deleteFileForEntity(category.getImagePath());
        }
    }

    @Override
    public ImageDto getFileForCategory(Integer id) {
        Category category = getById(id);
        return storageService.getByteArrayFromStorageFile(category.getImagePath());
    }
}
