package com.ukma.springproject.service;

import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.domain.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void create(ProductDTO product);
    void delete(Long id);
    void approve(Long applicationId, String userEmail);
    ProductDTO findById(Long id);
    List<ProductDTO> findAll();
    List<ProductDTO> findByCategory(String categoryName);
    List<ProductDTO> findByDeveloper(Long id);

}
