package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Base64;

@Data
@AllArgsConstructor
public class ImageDto {
    private String fileName;
    private String fileInBase64;
}
