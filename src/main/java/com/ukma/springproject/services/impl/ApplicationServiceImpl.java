package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }


    @Override
    public void delete(Application application) {
        applicationRepository.delete(application);
    }

    @Override
    public Application findById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application " + applicationId + " does not exist"));
    }

    @Override
    public List<Application> getAllApplicationsByDeveloper(User user) {
        return applicationRepository.findApplicationByDeveloper(user);
    }

    @Override
    public List<Application> getAllApplications() {
        return (List<Application>) applicationRepository.findAll();
    }
}
