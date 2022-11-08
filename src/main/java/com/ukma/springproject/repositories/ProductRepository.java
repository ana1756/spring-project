package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByApplicationCategory(Category category);

    List<Product> findAllByApplicationDeveloper(User user);
}
