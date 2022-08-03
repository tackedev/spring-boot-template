package com.tackedev.springboottemplate.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponseEntity(NOT_FOUND, e.getMessage(), e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        return new ErrorResponseEntity(FORBIDDEN, e.getMessage(), e);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseEntity handleResourceNotFoundException(Exception e) {
        return new ErrorResponseEntity(INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }

}
