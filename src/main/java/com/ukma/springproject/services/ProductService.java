package com.ukma.springproject.services;

import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import java.util.List;

public interface ProductService {

    Product save(Product product);
    Product edit(Product product);
    void delete(Product product);
    Product findById(Long productId);
    List<Product> findAll();
    List<Product> findAllByCategory(Category category);
    List<Product> findByDeveloper(User user);

}
