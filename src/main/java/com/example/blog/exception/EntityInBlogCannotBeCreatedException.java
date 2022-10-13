package com.example.blog.exception;

import lombok.Data;

@Data
public class EntityInBlogCannotBeCreatedException extends RuntimeException{
    public EntityInBlogCannotBeCreatedException(String message){
        super(message);
    }
    public EntityInBlogCannotBeCreatedException(String message, Throwable cause){
        super(message,cause);
    }

}
