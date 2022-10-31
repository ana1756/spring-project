package com.ukma.springproject.services;

import com.ukma.springproject.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre save(Genre genre);
    void delete(Genre genre);
    Genre findById(Long id);
    List<Genre> findAll();
}
