package com.example.blog.exception;

import lombok.Data;

import java.util.List;

@Data
public class EntityInBlogCannotBeDeletedException extends RuntimeException{
    public EntityInBlogCannotBeDeletedException(String message){
        super(message);
    }
    public EntityInBlogCannotBeDeletedException(String entityName, String message){
        super(entityName + " cannot be deleted cause : " + message);
    }
    public EntityInBlogCannotBeDeletedException(String message, Throwable cause){
        super(message,cause);
    }

}
