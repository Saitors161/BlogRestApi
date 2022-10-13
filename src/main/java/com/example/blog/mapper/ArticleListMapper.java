package com.example.blog.mapper;

import com.example.blog.dto.ArticleDto;
import com.example.blog.model.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ArticleMapper.class)
public interface ArticleListMapper {
    List<ArticleDto> toDto(List<Article> articleList);
    List<Article> toModel(List<ArticleDto> articleDtoList);
}
