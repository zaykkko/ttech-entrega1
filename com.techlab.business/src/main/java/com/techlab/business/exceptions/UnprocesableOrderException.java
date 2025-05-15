package com.techlab.business.exceptions;

public class UnprocesableOrderException extends RuntimeException {
    public UnprocesableOrderException(String message) {
        super(message);
    }
}
