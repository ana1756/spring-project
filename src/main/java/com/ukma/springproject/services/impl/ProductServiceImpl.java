package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ProductRepository;
import com.ukma.springproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository prodRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    @Override
    public Product save(Product product) {
        return prodRepository.save(product);
    }

    @Override
    public Product edit(Product product) {
        return prodRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        prodRepository.delete(product);
    }

    @Override
    public Product findById(Long productId) {
       return prodRepository.findById(productId)
               .orElseThrow(() -> new RuntimeException("Product " + productId + " does not exist"));
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) prodRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return prodRepository.findAllByApplicationCategory(category);
    }

    @Override
    public List<Product> findByDeveloper(User user) {
        return prodRepository.findAllByApplicationDeveloper(user);
    }
}
