package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.services.ApplicationService;
import com.ukma.springproject.services.exceptions.ApplicationException;
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
                .orElseThrow(() -> new ApplicationException("Application by id " + applicationId + " does not exist"));
    }

    @Override
    public List<Application> getAllApplicationsByDeveloper(Long id) {
        return applicationRepository.findApplicationByDeveloperId(id);
    }

    @Override
    public List<Application> getAllApplications() {
        return (List<Application>) applicationRepository.findAll();
    }

    @Override
    public Application edit(Application application) {
        return applicationRepository.findById(application.getId())
                .map(app -> applicationRepository.save(application)).orElseThrow(() -> new ApplicationException
                        ("Entry with id " + application.getId() + " did not exist"));
    }
}
