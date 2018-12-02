package com.konovalov.hello.controller;

import com.konovalov.hello.domain.ApiError;
import com.konovalov.hello.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> handleBadRequestExeprion() {
        return new ResponseEntity<>(new ApiError("error input", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
