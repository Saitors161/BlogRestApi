package com.example.blog.exception;

import lombok.Data;

import java.util.List;

@Data
public class EntityInBlogCannotBeCreatedException extends RuntimeException{
    public EntityInBlogCannotBeCreatedException(String message){
        super(message);
    }
    public EntityInBlogCannotBeCreatedException(String entityName, List<String> listError){
        super(entityName + " cannot be created cause :" + listError.toString());
    }
    public EntityInBlogCannotBeCreatedException(String message, Throwable cause){
        super(message,cause);
    }

}
