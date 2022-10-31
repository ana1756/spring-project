package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.PurchaseRepository;
import com.ukma.springproject.services.EmailService;
import com.ukma.springproject.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final EmailService emailService;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(EmailService emailService, PurchaseRepository purchaseRepository) {
        this.emailService = emailService;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }


    @Override
    public Purchase edit(Purchase purchase) {
        return purchaseRepository.findById(purchase.getId())
                .orElseThrow(()-> new RuntimeException("Purchase " + purchase.getId() + " does not exist"));
    }

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Purchase " + id + " does not exist"));
    }

    @Override
    public List<Purchase> findByUser(User user) {
        return purchaseRepository.findAllByUser(user);
    }

    @Override
    public List<Purchase> findAllPurchases() {
        return (List<Purchase>) purchaseRepository.findAll();
    }
}
