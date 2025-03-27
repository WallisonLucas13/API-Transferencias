package com.example.transfer.api.exceptions;

public class UserNotAllowedToMakeTransfersException extends RuntimeException {
    public UserNotAllowedToMakeTransfersException(String message) {
        super(message);
    }
}
