package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @ManyToOne
    @JoinColumn(name = "key")
    private  Key key;

    @ManyToOne
    @JoinColumn(name = "user")
    private  User user;

}
