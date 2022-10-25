package com.ukma.springproject.services;

import com.ukma.springproject.domain.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    void delete(Category category);
    List<Category> findAll();

}
