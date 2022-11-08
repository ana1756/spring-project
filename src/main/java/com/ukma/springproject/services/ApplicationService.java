package com.ukma.springproject.services;

import com.ukma.springproject.domain.Application;

import java.util.List;

public interface ApplicationService {

    Application save(Application application);
    void delete(Application application);
    Application findById(Long applicationId);
    List<Application> getAllApplicationsByDeveloper(Long id);
    List<Application> getAllApplications();
    Application edit(Application application);
}