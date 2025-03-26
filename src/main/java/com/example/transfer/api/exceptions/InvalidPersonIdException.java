package com.example.transfer.api.exceptions;

public class InvalidPersonIdException extends RuntimeException {
    public InvalidPersonIdException(String message) {
        super(message);
    }
}
