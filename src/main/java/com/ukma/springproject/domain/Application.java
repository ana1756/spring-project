package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class Application {

    private final int id;
    private final String name;
    private final double price;
    private final Timestamp date_created;
    private final User developer;


}
