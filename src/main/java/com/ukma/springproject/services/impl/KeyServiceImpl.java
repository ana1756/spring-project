package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.repositories.KeyRepository;
import com.ukma.springproject.services.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    private final KeyRepository keyRepository;

    @Autowired
    public KeyServiceImpl(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public Key save(Key key) {
        return keyRepository.save(key);
    }

    @Override
    public void delete(Key key) {
        keyRepository.delete(key);
    }

    @Override
    public List<Key> findAllByProduct(Product product) {
        return keyRepository.findAllByProduct(product);
    }

}
