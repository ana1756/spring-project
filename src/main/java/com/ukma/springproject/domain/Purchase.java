package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class Purchase {

    private final int id;
    private final Timestamp dateCreated;
    private final Key key;
    private final User user;

}
