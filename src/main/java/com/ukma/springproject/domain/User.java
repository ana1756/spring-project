package com.ukma.springproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    public static final String ADMIN = "admin";
    public static final String DEVELOPER = "developer";
    public static final String CLIENT = "client";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "role", nullable = false)
    private String role;

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
