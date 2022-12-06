package com.ukma.springproject.service;

import com.ukma.springproject.domain.Category;
import java.util.List;

public interface CategoryService {

    void create(Category category);
    void delete(String name);
    List<Category> findAll();

}
