package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.exceptions.BadRequestException;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Application> findAllByPublished(boolean flag) {
        return repository.readAllByPublished(flag);
    }

    @Override
    public void create(Application application, User developer) {
        application.setDateCreated(new Date());
        application.setDeveloper(developer);
        if (application.getName() == null || application.getImage() == null ||
                application.getDescription() == null || application.getPrice() == null
                || (application.getPrice() < 0))
            throw new BadRequestException(
                    "One of the sent fields contained illegal value or was not filled! It is not allowed!"
            );
        repository.save(application);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Application findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ApplicationNotFoundException("Application by requested id does not exist"));
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications = new ArrayList<>();
        repository.findAll().forEach(applications::add);
        return applications;
    }

    @Override
    public List<Application> findByDeveloper(Long id) {
        return repository.readAllByDeveloperId(id);
    }


    @Override
    public List<Application> getAllApplications() {
        return findAll();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
