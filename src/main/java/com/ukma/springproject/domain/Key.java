package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Key {

    private final int id;
    private final String value;
    private final Product product;
}
