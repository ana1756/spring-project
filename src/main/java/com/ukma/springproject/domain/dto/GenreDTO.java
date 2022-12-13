package com.ukma.springproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {

    private String name;

    @JsonIgnore
    private List<ApplicationDTO> applications;

}
