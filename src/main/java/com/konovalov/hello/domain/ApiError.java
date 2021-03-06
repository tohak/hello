package com.konovalov.hello.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private HttpStatus status;
}
