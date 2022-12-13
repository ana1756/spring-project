package com.ukma.springproject.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;

    private String content;

    private Date dateCreated;

    private ProductDTO product;

    private UserDTO user;

}
