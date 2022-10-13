package com.example.blog.controller;

import com.example.blog.dto.CategoryDto;
import com.example.blog.dto.ImageDto;
import com.example.blog.mapper.CategoryListMapper;
import com.example.blog.mapper.CategoryMapper;
import com.example.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Tag(name = "Category", description = "CRUD for categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final CategoryListMapper categoryListMapper;

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create category", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Category created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    })
    })
    CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryService.save(categoryMapper.toModel(categoryDto)));
    }


    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get category by id", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category is got",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    })
    })
    CategoryDto getCategoryById(@PathVariable Integer id) {
        return categoryMapper.toDto(categoryService.getById(id));
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all categories", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categories is got",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
                    })
    })
    List<CategoryDto> getAllCategories() {
        return categoryListMapper.toDto(categoryService.getAll());
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete category by id", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Category deleted")
    })
    void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update category ", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    })
    })
    CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryService.update(categoryMapper.toModel(categoryDto)));
    }


    @PostMapping("/categories/{id}/image")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save image data by category id", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Image saved")
    })
    void saveImageForCategory(@PathVariable Integer id, @RequestBody MultipartFile file) {
        categoryService.saveFileForCategory(id, file);
    }

    @DeleteMapping("/categories/{id}/image")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete image by category id", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image deleted")
    })
    void deleteImageForCategory(@PathVariable Integer id) {
        categoryService.deleteFileForCategory(id);
    }

    @GetMapping("/categories/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get image by category id", tags = "Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image is got")
    })
    ImageDto getImageForCategory(@PathVariable Integer id) {
        return categoryService.getFileForCategory(id);
    }
}
