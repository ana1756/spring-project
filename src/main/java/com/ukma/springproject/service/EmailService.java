package com.ukma.springproject.service;

public interface EmailService {
    boolean sendEmail(String to, String subject, String message);
}
