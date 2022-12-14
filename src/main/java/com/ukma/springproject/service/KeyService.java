package com.ukma.springproject.service;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface KeyService {

    void create(Key key);
    Key createFromProduct(Product product, User user);
    void delete(Long id);
    Key findById(Long id);
    List<Key> findByProduct(Long id);
    List<Key> findByUser(Long id);

}
