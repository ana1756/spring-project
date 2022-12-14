package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.KeyRepository;
import com.ukma.springproject.service.KeyService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Key createFromProduct(Product product, User user) {
        String str = "key::" +
                product.getApplication().getName().charAt(0) +
                product.getId() + "::" +
                user.getId() +
               Math.round(Math.random() * 1000) ;
        Key key = new Key();
        key.setValue(str);
        key.setDuration(100);
        key.setDateCreated(new Date());
        key.setProduct(product);
        key.setUser(user);
        repository.save(key);
        return key;

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
