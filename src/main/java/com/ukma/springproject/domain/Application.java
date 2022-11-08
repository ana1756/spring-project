package com.ukma.springproject.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    private String name;

    @Column(name = "price", nullable = false)
    @Min(value = 0L)
    private double price;

    @Column(name = "date_created")
    private Timestamp dateCreated;


    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    @NotNull
    private Category category;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "developer")
    @NotNull
    private User developer;

    @ManyToMany
    @JoinTable(
            name = "genres",
            joinColumns = @JoinColumn(name = "application"),
            inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<Genre> genres;

}
