package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAllByUser(User user);

}
