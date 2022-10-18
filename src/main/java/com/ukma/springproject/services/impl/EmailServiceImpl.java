package com.ukma.springproject.services.impl;

import com.ukma.springproject.services.EmailService;
import org.springframework.stereotype.Service;

//If commented, application uses email service from autoconfiguration
//@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String message) {
        System.out.println("Custom email service");
    }
}