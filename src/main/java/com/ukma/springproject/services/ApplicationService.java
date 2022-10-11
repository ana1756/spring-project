package com.ukma.springproject.services;

import com.ukma.springproject.domain.Application;

public interface ApplicationService {
    void insert(Application application);
    void update(int applicationId, Application application);
    void delete(int applicationId);

    Application findById(int applicationId);
}
