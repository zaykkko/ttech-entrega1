package com.techlab.business.exceptions;

public class InvalidOrderDataException extends RuntimeException {
    public InvalidOrderDataException(String message) {
        super(message);
    }
}
