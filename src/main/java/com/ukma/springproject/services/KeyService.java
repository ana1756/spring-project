package com.ukma.springproject.services;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;

import java.util.List;

public interface KeyService {

    Key save(Key key);
    void delete(Key key);
    List<Key> findAllByProduct(Product product);

}
