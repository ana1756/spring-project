package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class Comment {

    private final int id;
    private final String text;
    private final Timestamp dateCreated;
    private final User user;
    private final Product product;

}
