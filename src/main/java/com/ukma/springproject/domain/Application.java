package com.ukma.springproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "applications")
public class Application implements Serializable  {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @Column(name = "applicationa_name", length = 60, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(length = 1000, nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    private Date dateCreated = new Date();

    @Column(name = "image_path", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "dev_id", nullable = false)
    private User developer;

    @ManyToMany
    @JoinTable(
            name = "application_has_genre",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name"))
    @JsonIgnore
    @ToString.Exclude
    private List<Genre> genres;

    @OneToOne(mappedBy = "application")
    @JsonIgnore
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Application that = (Application) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
