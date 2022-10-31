package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private  String text;

    @Column(name = "date_created", nullable = false)
    private  Timestamp dateCreated;

    @ManyToOne
    @JoinColumn(name = "user")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "product")
    private  Product product;

}
