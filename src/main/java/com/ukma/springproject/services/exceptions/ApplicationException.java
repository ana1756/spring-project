package com.ukma.springproject.services.exceptions;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String msg) {
        super(msg);
    }
}
