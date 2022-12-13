package com.ukma.springproject.exceptions;

public class ApplicationNotFoundException extends EntityNotFoundException {
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
