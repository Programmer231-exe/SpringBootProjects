package com.dateformatchecker.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
