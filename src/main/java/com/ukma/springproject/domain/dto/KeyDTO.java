package com.ukma.springproject.domain.dto;
import lombok.Data;

import java.util.Date;

@Data
public class KeyDTO {

    private Long id;

    private String value;

    private int duration;

    private Date dateCreated;

    private ProductDTO product;

    private UserDTO user;

}
