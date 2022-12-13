package com.ukma.springproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class ProductDTO {

    private Long id;

    private Date dateCreated;

    private ApplicationDTO application;

    private CategoryDTO category;

    private UserDTO admin;

    @JsonIgnore
    private List<KeyDTO> keys;

    @JsonIgnore
    private List<CommentDTO> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductDTO product = (ProductDTO) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
