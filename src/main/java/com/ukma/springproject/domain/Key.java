package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "keys")
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "key_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "value", nullable = false, unique = true)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

}
