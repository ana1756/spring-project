package com.ukma.springproject.autoconfiguration;

import com.ukma.springproject.services.EmailService;
import org.springframework.beans.factory.annotation.Value;

public class EmailServiceDefaultImpl implements EmailService {

    @Value("${mail.public}")
    private String publicKey;

    @Value("${mail.private}")
    private String privateKey;

    @Override
    public void sendEmail(String to, String subject, String message) {
        System.out.println("Default email service from starter\n" +
                           "public: " + publicKey + '\n' +
                           "private: " + privateKey + '\n'
        );
    }
}