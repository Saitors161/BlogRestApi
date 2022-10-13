package com.example.blog.mapper;

import com.example.blog.dto.CategoryDto;
import com.example.blog.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface CategoryListMapper {
    List<Category> toModel(List<CategoryDto> categoryDtoList);
    List<CategoryDto> toDto(List<Category> categoryList);
}
