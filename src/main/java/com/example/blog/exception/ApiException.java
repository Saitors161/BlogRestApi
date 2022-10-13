package com.example.blog.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiException {
    private final String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    public ApiException(String message) {
        this.message = message;
    }
}
