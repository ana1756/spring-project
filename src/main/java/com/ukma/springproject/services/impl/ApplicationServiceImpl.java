package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.services.ApplicationService;
import com.ukma.springproject.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private EmailService emailService;

    @Override
    public void insert(Application application) {

    }

    @Override
    public void update(int applicationId, Application application) {

    }

    @Override
    public void delete(int applicationId) {

    }

    @Override
    public Application findById(int applicationId) {
        return null;
    }
}
