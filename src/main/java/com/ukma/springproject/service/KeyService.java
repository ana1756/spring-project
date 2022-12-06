package com.ukma.springproject.service;

import com.ukma.springproject.domain.Key;
import java.util.List;

public interface KeyService {

    void create(Key key);
    void delete(Long id);
    Key findById(Long id);
    List<Key> findByProduct(Long id);
    List<Key> findByUser(Long id);

}
