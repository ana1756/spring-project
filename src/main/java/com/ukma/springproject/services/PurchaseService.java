package com.ukma.springproject.services;

import com.ukma.springproject.domain.Purchase;

public interface PurchaseService {
    boolean proceedWithPurchase(Purchase purchase);
}
