package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface EmailService {
    boolean sendEmail(String to, String subject, String message);
    void sendAfterApplicationPublishing(Application app);
    void sendAfterApplicationCreation(Application app, List<String> admins);
    void sendAfterKeyBuying(Key key);

}
