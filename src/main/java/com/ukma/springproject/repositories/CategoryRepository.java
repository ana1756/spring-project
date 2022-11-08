package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
}
