package com.example.blog.mapper;

import com.example.blog.dto.CategoryDto;
import com.example.blog.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper (componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category toModel(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
