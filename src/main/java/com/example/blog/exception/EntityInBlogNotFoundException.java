package com.example.blog.exception;

import lombok.Data;

@Data
public class EntityInBlogNotFoundException extends RuntimeException{
    public EntityInBlogNotFoundException(String message){
        super(message);
    }
    public EntityInBlogNotFoundException(String entityName, String parameter, String value){
        super(entityName +" with " + parameter + " = " + value + " not found");
    }
    public EntityInBlogNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

}
