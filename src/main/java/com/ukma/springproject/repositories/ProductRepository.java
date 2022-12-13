package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> readAllByCategory_Name(String name);
    List<Product> readAllByAdmin_Id(Long id);
}
