package com.ukma.springproject.services;

public interface EmailService {
    void sendEmail(String to,String subject, String message);
}
