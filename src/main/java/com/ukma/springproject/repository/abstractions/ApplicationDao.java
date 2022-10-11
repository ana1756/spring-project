package com.ukma.springproject.repository.abstractions;

import com.ukma.springproject.domain.Application;

public interface ApplicationDao {
    void insert(Application application);
    void update(int applicationId, Application application);
    void delete(int applicationId);

    Application findById(int applicationId);
}
