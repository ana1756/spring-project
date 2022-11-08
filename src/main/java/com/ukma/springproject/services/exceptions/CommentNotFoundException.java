package com.ukma.springproject.services.exceptions;

public class CommentNotFoundException extends RuntimeException {
    CommentNotFoundException(String msg) {
        super(msg);
    }
}
