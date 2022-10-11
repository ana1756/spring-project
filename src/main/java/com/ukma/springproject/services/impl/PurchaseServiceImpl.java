package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.repository.PurchaseRepository;
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
    public void insert(Purchase purchase) {

//        purchaseRepository.save(purchase);
//        emailService.sendEmail("", "", "");
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
