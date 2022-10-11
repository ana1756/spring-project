package com.ukma.springproject.services;

import com.ukma.springproject.domain.Purchase;

import java.util.*;

public interface PurchaseService {
    void insert(Purchase purchase);
    void update(int purchaseId, Purchase purchase);
    void delete(int purchaseId);

    Purchase findById(int purchaseId);
    List<Purchase> findByUserId(int userId);
}
