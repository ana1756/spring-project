package com.ukma.springproject.services.impl;

import com.ukma.springproject.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "mail", name = "useDefaultMail", havingValue = "false" )
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String message) {
        System.out.println("Custom email service");
    }
}