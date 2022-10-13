package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String header;
    private String description;
}
