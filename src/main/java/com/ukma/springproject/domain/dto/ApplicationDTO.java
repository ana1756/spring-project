package com.ukma.springproject.domain.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class ApplicationDTO {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private Date dateCreated = new Date();

    private String image;

    private UserDTO developer;

    private List<GenreDTO> genres;

    private ProductDTO product;
}
