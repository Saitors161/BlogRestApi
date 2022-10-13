package com.example.blog.service;

import com.example.blog.dto.ImageDto;
import com.example.blog.model.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    Article getById(Integer id);

    List<Article> getAll();

    void deleteById(Integer id);

    Article update(Article article);

    void saveFileForArticle(Integer id, MultipartFile file);

    void deleteFileForArticle(Integer id);

    ImageDto getFileForArticle(Integer id);
}
