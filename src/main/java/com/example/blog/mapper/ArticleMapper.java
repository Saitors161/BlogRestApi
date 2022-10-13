package com.example.blog.mapper;

import com.example.blog.dto.ArticleDto;
import com.example.blog.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ArticleMapper {
    ArticleDto toDto(Article article);
    Article toModel(ArticleDto articleDto);
}
