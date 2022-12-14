package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface ProductService {

    void create(Product product);
    void createFromApplication(Application application, User admin, Category category);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(String categoryName);
    List<Product> getAllSortedAndFiltered(String category, String genre, String sorting);

}
