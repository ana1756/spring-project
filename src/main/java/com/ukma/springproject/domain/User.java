package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;


@Data
@RequiredArgsConstructor
public class User {

    public enum Role {
        USER, ADMIN, DEVELOPER
    }

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Timestamp dateCreated;
    private final Role role;
}
