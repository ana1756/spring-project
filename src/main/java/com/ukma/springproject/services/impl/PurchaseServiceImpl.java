package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Purchase;
import com.ukma.springproject.repository.PurchaseRepository;
import com.ukma.springproject.services.EmailService;
import com.ukma.springproject.repository.abstractions.PurchaseDao;
import com.ukma.springproject.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDao purchaseDao;
    private EmailService emailService;

    public PurchaseServiceImpl(@Autowired PurchaseDao dao) {
        this.purchaseDao = dao;
    }

    @Override
    public boolean proceedWithPurchase(Purchase purchase) {
        //emailService.sendEmail("", "", "");
        return false;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
