package com.ukma.springproject.repository;

import com.ukma.springproject.domain.Product;
import com.ukma.springproject.repository.abstractions.ProductDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements ProductDao {
    @Override
    public void insert(Product product) {

    }

    @Override
    public void update(int productId, Product product) {

    }

    @Override
    public void delete(int productId) {

    }

    @Override
    public Product findById(int productId) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findAllByCategoryId(int categoryId) {
        return null;
    }
}
