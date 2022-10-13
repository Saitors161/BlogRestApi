package com.example.blog.exception;

import lombok.Data;

@Data
public class FileInBlogAppNotFoundException extends RuntimeException{
    public FileInBlogAppNotFoundException(String message){
        super(message);
    }
    public FileInBlogAppNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

}
