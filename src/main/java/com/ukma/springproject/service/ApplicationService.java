package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import java.util.List;

public interface ApplicationService {

    void create(Application application);
    void update(Long id, Application application);
    void delete(Long id);
    Application findById(Long id);
    List<Application> findAll();
    List<Application> findByDeveloper(Long id);

}
