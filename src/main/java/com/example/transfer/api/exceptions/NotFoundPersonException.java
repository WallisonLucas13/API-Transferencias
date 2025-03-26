package com.example.transfer.api.exceptions;

public class NotFoundPersonException extends RuntimeException {
    public NotFoundPersonException(String message) {
        super(message);
    }
}
