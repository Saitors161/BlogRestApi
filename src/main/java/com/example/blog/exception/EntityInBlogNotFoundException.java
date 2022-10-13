package com.example.blog.exception;

import lombok.Data;

@Data
public class EntityInBlogNotFoundException extends RuntimeException{
    public EntityInBlogNotFoundException(String message){
        super(message);
    }
    public EntityInBlogNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

}
