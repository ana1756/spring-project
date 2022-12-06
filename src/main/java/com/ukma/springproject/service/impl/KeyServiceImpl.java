package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.repositories.KeyRepository;
import com.ukma.springproject.service.KeyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    private KeyRepository repository;

    public KeyServiceImpl(KeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Key key) {
        repository.save(key);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Key findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Key> findByProduct(Long id) {
        return repository.readAllByProduct_Id(id);
    }

    @Override
    public List<Key> findByUser(Long id) {
        return repository.readAllByUser_Id(id);
    }
}
