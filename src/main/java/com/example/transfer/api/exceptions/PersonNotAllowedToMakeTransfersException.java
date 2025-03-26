package com.example.transfer.api.exceptions;

public class PersonNotAllowedToMakeTransfersException extends RuntimeException {
    public PersonNotAllowedToMakeTransfersException(String message) {
        super(message);
    }
}
