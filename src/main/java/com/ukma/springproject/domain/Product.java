package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "date_created", nullable = false)
    private  Timestamp dateCreated;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "application")
    private  Application application;

    @ManyToOne
    @JoinColumn(name = "admin")
    private  User admin;

}
