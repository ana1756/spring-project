package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repository.abstractions.ApplicationDao;
import com.ukma.springproject.repository.abstractions.ProductDao;
import com.ukma.springproject.repository.abstractions.UserDao;
import com.ukma.springproject.services.ApplicationService;
import com.ukma.springproject.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EmailService emailService;

    @Override
    public boolean apply(Application application) {
        return false;
    }

    @Override
    public boolean approve(Application application) {
        return false;
    }

    @Override
    public boolean deny(Application application) {
        return false;
    }
}
