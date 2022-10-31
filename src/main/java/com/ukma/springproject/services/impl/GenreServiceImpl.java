package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Genre;
import com.ukma.springproject.repositories.GenreRepository;
import com.ukma.springproject.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
         genreRepository.delete(genre);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre " + id + " does not exist"));
    }

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepository.findAll();
    }

}
