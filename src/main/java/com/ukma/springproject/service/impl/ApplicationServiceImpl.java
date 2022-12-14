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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    private final Set<String> allowed;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository repository, Set<String> allowed) {
        this.repository = repository;
        this.allowed = allowed;
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
    public List<Application> findByDeveloperSortedAndFiltered(Long id, String genre, String sorting) {
        if (!allowed.contains(sorting))
            throw new BadRequestException("Invalid sorting method");
        return repository.readAllByDeveloperId(id)
                .stream().filter(x ->
                        x.getGenres().stream().anyMatch(y -> y.getName().equals(genre)))
                .sorted((o1, o2) -> compareApps(o1, o2, sorting))
                .collect(Collectors.toList());
    }


    @Override
    public List<Application> getAllApplications() {
        return findAll();
    }

    @Override
    public List<Application> getAllSortedAndFiltered(String genre, String sorting) {
        if (!allowed.contains(sorting))
            throw new BadRequestException("Invalid sorting method");
        return repository.readAllByPublished(false)
                .stream().filter(x ->
                        x.getGenres().stream().anyMatch(y -> y.getName().equals(genre)))
                .sorted((o1, o2) -> compareApps(o1, o2, sorting))
                .collect(Collectors.toList());
    }

    private static int compareApps(Application o1, Application o2, String sorting) {
        var compResult = 0;
        switch (sorting) {
            case "lowPrice":
                compResult = Double.compare(o1.getPrice(), o2.getPrice());
                break;
            case "highPrice":
                compResult = Double.compare(o2.getPrice(), o1.getPrice());
                break;
            case "aToz":
                compResult = o1.getName().compareTo(o2.getName());
                break;
            case "zToa":
                compResult = o2.getName().compareTo(o1.getName());
                break;
            case "oldToNew":
                compResult = o1.getDateCreated().compareTo(o2.getDateCreated());
                break;
            case "newToOld":
                compResult = o2.getDateCreated().compareTo(o1.getDateCreated());
                break;
            default:
                throw new BadRequestException("Unexpected value: " + sorting);
        }
        return compResult;
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
