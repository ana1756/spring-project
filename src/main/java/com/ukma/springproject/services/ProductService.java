package com.ukma.springproject.services;

import com.ukma.springproject.domain.Product;

import java.util.List;

public interface ProductService {
    void insert(Product product);
    void update(int productId, Product product);
    void delete(int productId);

    Product findById(int productId);
    List<Product> findAll();
    List<Product> findAllByCategoryId(int categoryId);
}
