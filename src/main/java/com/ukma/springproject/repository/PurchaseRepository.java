package com.ukma.springproject.repository;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.repository.abstractions.PurchaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository implements PurchaseDao {
    @Override
    public void insert(Purchase purchase) {

    }

    @Override
    public void update(int purchaseId, Purchase purchase) {

    }

    @Override
    public void delete(int purchaseId) {

    }

    @Override
    public Purchase findById(int purchaseId) {
        return null;
    }

    @Override
    public List<Purchase> findByUserId(int userId) {
        return null;
    }
}
