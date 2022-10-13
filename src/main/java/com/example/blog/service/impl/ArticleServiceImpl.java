package com.example.blog.service.impl;

import com.example.blog.dto.ImageDto;
import com.example.blog.exception.EntityInBlogCannotBeCreatedException;
import com.example.blog.exception.EntityInBlogNotFoundException;
import com.example.blog.model.Article;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.service.ArticleService;
import com.example.blog.service.CategoryService;
import com.example.blog.service.FileStorageService;
import com.example.blog.util.enums.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final FileStorageService storageService;
    private final String currentNameEntity = EntityType.ARTICLE.toString();

    @Override
    public Article save(Article article) {
        checkDataArticleBeforeSave(article);
        article.setCategory(categoryService.getById(article.getCategory().getId()));
        return articleRepository.save(article);
    }

    private void checkDataArticleBeforeSave(Article article) {
        List<String> errors = new ArrayList<>();
        if (article.getCategory() == null) {
            errors.add("Article with empty category cannot be created");
        } else {
            if (article.getCategory().getId() == null
                    || article.getCategory().getId().equals(0)) {
                errors.add("Article with empty category id cannot be created");
            }
        }

        if (article.getHeader() == null || article.getHeader().equals("")) {
            errors.add("Article with empty header cannot be created");
        }
        if (!errors.isEmpty()) {
            throw new EntityInBlogCannotBeCreatedException(errors.toString());
        }
    }

    @Override
    public Article getById(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityInBlogNotFoundException("Article with id " + id + " not found"));
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Article update(Article article) {
        if (article.getId() != null && articleRepository.existsById(article.getId())) {
            article = save(article);
            return articleRepository.findById(article.getId()).get();
        }
        throw new EntityInBlogNotFoundException("Article with id " + article.getId() + " not found");
    }

    @Override
    public void saveFileForArticle(Integer id, MultipartFile file) {
        Article article = getById(id);
        if (article.getImagePath() != null && !article.getImagePath().equals("")) {
            storageService.deleteFileForEntity(article.getImagePath());
        }
        article.setImagePath(storageService.saveFileForEntity(currentNameEntity, id, file));
        articleRepository.save(article);
    }

    @Override
    public void deleteFileForArticle(Integer id) {
        Article article = getById(id);
        if (article.getImagePath() != null && !article.getImagePath().equals("")) {
            storageService.deleteFileForEntity(article.getImagePath());
        }
    }

    @Override
    public ImageDto getFileForArticle(Integer id) {
        Article article = getById(id);
        return storageService.getByteArrayFromStorageFile(article.getImagePath());
    }
}
