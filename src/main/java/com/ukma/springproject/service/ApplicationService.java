package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface ApplicationService {

    void create(Application application, User developer);
    List<Application> findAllByPublished(boolean flag);
    void delete(Long id);
    Application findById(Long id);
    List<Application> findAll();
    List<Application> findByDeveloper(Long id);
    List<Application> getAllApplications();

}
