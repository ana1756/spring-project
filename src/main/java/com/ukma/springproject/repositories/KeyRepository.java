package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {

    List<Key>findAllByProduct(Product product);
}
