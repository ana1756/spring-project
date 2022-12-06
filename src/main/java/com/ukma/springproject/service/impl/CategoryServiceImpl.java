package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Category;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Category category) {
        repository.save(category);
    }

    @Override
    public void delete(String name) {
        repository.deleteById(name);
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);
        return categories;
    }
}
