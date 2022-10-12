package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.services.EmailService;
import com.ukma.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private EmailService emailService;

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(int userId, User user) {

    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public User findById(int userId) {
        return null;
    }

    @Override
    public User findByName(String firstAndLastname) {
        return null;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
