package com.ukma.springproject.services;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface ApplicationService {

    Application save(Application application);
    void delete(Application application);
    Application findById(Long applicationId);
    List<Application> getAllApplicationsByDeveloper(User user);
    List<Application> getAllApplications();
}