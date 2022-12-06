package com.ukma.springproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="genres")
public class Genre implements Serializable {

    @Id
    @Column(name = "genre_name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Application> applications;

}
