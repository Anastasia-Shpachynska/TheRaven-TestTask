package com.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniversalException extends RuntimeException {
    public UniversalException(String message) {
        super(message);
    }
}
