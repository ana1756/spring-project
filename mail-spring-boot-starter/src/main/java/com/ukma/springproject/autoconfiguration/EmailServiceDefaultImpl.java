package com.ukma.springproject.autoconfiguration;

import com.ukma.springproject.services.EmailService;

public class EmailServiceDefaultImpl implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String message) {
        System.out.println("Default email service from starter");
    }
}