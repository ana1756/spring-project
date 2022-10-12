package com.ukma.springproject.repository;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repository.abstractions.ApplicationDao;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepository implements ApplicationDao {
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
