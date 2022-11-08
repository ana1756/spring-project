package com.ukma.springproject.services.exceptions;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(String msg) {
        super(msg);
    }
}
