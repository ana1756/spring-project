package com.ukma.springproject.service;

import com.ukma.springproject.domain.Product;
import java.util.List;

public interface ProductService {

    void create(Product product);
    void update(Long id, Product product);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(String categoryName);

}
