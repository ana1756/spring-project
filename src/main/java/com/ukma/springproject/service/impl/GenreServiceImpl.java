package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Genre;
import com.ukma.springproject.repositories.GenreRepository;
import com.ukma.springproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Genre genre) {
        repository.save(genre);
    }

    @Override
    public void delete(String name) {
        repository.delete(repository.findById(name).get());
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        repository.findAll().forEach(genres::add);
        return genres;
    }
}
