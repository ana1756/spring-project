package com.ukma.springproject.services;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;

import java.util.List;

public interface KeyService {
    void insert(Key key);
    void update(int keyId, Key key);
    void delete(int keyId);

    Key findByProductId(int productId);
    List<Key> findAllByProductId(int productId);
}
