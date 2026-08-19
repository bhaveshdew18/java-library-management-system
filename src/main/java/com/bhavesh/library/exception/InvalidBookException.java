package com.bhavesh.library.exception;

public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }
}
