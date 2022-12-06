package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Application application) {
        repository.save(application);
    }

    @Override
    public void update(Long id, Application application) {
        // TODO: 02.12.2022
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Application findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications = new ArrayList<>();
        repository.findAll().forEach(applications::add);
        return applications;
    }

    @Override
    public List<Application> findByDeveloper(Long id) {
        return repository.readAllByDeveloper_Id(id);
    }
}
