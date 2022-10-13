package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiBlogExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EntityInBlogNotFoundException.class})
    public ResponseEntity<Object> handleApiEntityNotFoundException(EntityInBlogNotFoundException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EntityInBlogCannotBeCreatedException.class})
    public ResponseEntity<Object> handleApiEntityCannotBeCreatedException(EntityInBlogCannotBeCreatedException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FailedToSaveFileException.class})
    public ResponseEntity<Object> handleApiFailedToSaveFileException(FailedToSaveFileException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FileInBlogAppNotFoundException.class})
    public ResponseEntity<Object> handleApiFileNotFoundException(FileInBlogAppNotFoundException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
