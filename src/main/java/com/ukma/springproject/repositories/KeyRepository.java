package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface KeyRepository extends CrudRepository<Key, Long> {
    List<Key> readAllByUser_Id(Long id);
    List<Key> readAllByProduct_Id(Long id);
}
