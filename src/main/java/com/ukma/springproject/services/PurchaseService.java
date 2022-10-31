package com.ukma.springproject.services;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.domain.User;

import java.util.*;

public interface PurchaseService {

    Purchase save(Purchase purchase);
    Purchase edit(Purchase purchase);
    Purchase findById(Long id);
    List<Purchase> findByUser(User user);
    List<Purchase> findAllPurchases();

}
