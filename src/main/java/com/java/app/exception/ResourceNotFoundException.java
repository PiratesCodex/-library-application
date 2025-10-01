package com.java.app.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L; // optional, but good practice

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

