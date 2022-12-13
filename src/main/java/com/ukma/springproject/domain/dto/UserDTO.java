package com.ukma.springproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ukma.springproject.domain.Role;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private Role role = Role.ROLE_USER;

    private Double balance = 0.0;

    private String password;

    private String avatarName = "default-avatar.png";

    private Date dateCreated = new Date();

    @JsonIgnore
    @ToString.Exclude
    private List<ApplicationDTO> applications;

    @JsonIgnore
    @ToString.Exclude
    private List<ProductDTO> products;

    @JsonIgnore
    @ToString.Exclude
    private List<CommentDTO> comments;

    @JsonIgnore
    @ToString.Exclude
    private List<KeyDTO> keys;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDTO user = (UserDTO) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}


