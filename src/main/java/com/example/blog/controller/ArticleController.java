package com.example.blog.controller;

import com.example.blog.dto.ArticleDto;
import com.example.blog.dto.ImageDto;
import com.example.blog.mapper.ArticleListMapper;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.model.Article;
import com.example.blog.service.ArticleService;
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
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Article", description = "CRUD for articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;
    private final ArticleListMapper articleListMapper;


    @PostMapping("/articles")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create article", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Article created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ArticleDto.class))
                    })
    })
    ArticleDto saveArticle(@RequestBody ArticleDto articleDto) {
        Article start = articleMapper.toModel(articleDto);
        Article saved = articleService.save(start);
        ArticleDto ds = articleMapper.toDto(saved);
        return ds;
    }

    @GetMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "find article by id", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Article is got",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ArticleDto.class))
                    })
    })
    ArticleDto getArticleById(@PathVariable Integer id) {
        return articleMapper.toDto(articleService.getById(id));
    }

    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all articles", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Articles is got",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class)))
                    })
    })
    List<ArticleDto> getAllArticles() {
        return articleListMapper.toDto(articleService.getAll());
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete article by id", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Article deleted")
    })
    void deleteArticleById(@PathVariable Integer id) {
        articleService.deleteById(id);
    }

    @PutMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update article", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Article updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ArticleDto.class))
                    })
    })
    ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleMapper.toDto(articleService.update(articleMapper.toModel(articleDto)));
    }

    @PostMapping("/articles/{id}/image")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save image for article", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Image saved")
    })
    void saveImageForArticle(@PathVariable Integer id, @RequestBody MultipartFile file) {
        articleService.saveFileForArticle(id, file);
    }

    @DeleteMapping("/articles/{id}/image")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete image for article", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Image deleted")
    })
    void deleteImageForArticle(@PathVariable Integer id) {
        articleService.deleteFileForArticle(id);
    }

    @GetMapping("/articles/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get image for article", tags = "Article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image is got",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ImageDto.class))
                    })
    })
    ImageDto getImageForArticle(@PathVariable Integer id) {
        return articleService.getFileForArticle(id);
    }
}
