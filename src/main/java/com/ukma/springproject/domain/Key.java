package com.ukma.springproject.domain;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "product_keys")
public class Key {

    @Id
    @GeneratedValue
    @Column(name = "key_id")
    private Long id;

    @Column(name = "key_value", nullable = false)
    private String value;

    @Column(nullable = false)
    private int duration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
