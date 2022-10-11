package com.ukma.springproject.repository.abstractions;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;

import java.util.List;

public interface KeyDao {
    void insert(Key key);
    void update(int keyId, Key key);
    void delete(int keyId);

    Key findByProductId(int productId);
    List<Key> findAllByProductId(int productId);
}
