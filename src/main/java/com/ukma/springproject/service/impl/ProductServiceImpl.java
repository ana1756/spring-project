package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ProductRepository;
import com.ukma.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Product product) {
        repository.save(product);
    }

    @Override
    public void createFromApplication(Application application, User admin, Category category){
        Product product = new Product();
        product.setApplication(application);
        product.setDateCreated(new Date());
        product.setAdmin(admin);
        product.setCategory(category);
        create(product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        return repository.readAllByCategory_Name(categoryName);
    }

}
