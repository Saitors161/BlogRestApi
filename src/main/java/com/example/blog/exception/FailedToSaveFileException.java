package com.example.blog.exception;

import lombok.Data;

@Data
public class FailedToSaveFileException extends RuntimeException{
    public FailedToSaveFileException(String message){
        super(message);
    }
    public FailedToSaveFileException(String message, Throwable cause){
        super(message,cause);
    }

}
