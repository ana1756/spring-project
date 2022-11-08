package com.ukma.springproject.services.exceptions;

public class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(String msg) {
        super(msg);
    }
}
