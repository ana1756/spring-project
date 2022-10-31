package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private  String name;

    @Column(name = "price", nullable = false)
    private  double price;

    @Column(name = "date_created")
    private Timestamp dateCreated;


    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "developer")
    private  User developer;

    @ManyToMany
    @JoinTable(
            name = "genres",
            joinColumns = @JoinColumn(name = "application"),
            inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<Genre> genres;

}
