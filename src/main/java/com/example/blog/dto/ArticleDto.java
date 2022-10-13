package com.example.blog.dto;

import com.example.blog.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ArticleDto {
    private Integer id;
    private String header;
    private String description;
    private CategoryDto category;
}
