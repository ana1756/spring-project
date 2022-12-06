package com.ukma.springproject.service;

import com.ukma.springproject.domain.Genre;
import java.util.List;

public interface GenreService {

    void create(Genre genre);
    void delete(String name);
    List<Genre> findAll();

}
