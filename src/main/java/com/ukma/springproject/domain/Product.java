package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class Product {

    private final int id;
    private final Timestamp datePublished;
    private final Application application;
    private final User admin;



}
