package com.ukma.springproject.repository.abstractions;

import com.ukma.springproject.domain.Purchase;

import java.util.*;

public interface PurchaseDao {
    void insert(Purchase purchase);
    void update(int purchaseId, Purchase purchase);
    void delete(int purchaseId);

    Purchase findById(int purchaseId);
    List<Purchase> findByUserId(int userId);
}
