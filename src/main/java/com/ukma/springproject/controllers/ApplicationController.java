package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.services.ApplicationService;
import com.ukma.springproject.services.exceptions.ApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationRepository repository;

    ApplicationController(ApplicationService applicationService, ApplicationRepository repository) {
        this.applicationService = applicationService;
        this.repository = repository;
    }

    @GetMapping("/all")
    List<Application> all() {
        return applicationService.getAllApplications();
    }

    @PostMapping("/create")
    Application createApplication(@RequestBody Application application) {
        return applicationService.save(application);
    }

    @GetMapping("/{id}")
    Application findById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @PutMapping("/edit")
    Application editApplication(@RequestBody Application application) {
        return applicationService.edit(application);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        var dummy = new Application();
        dummy.setId(id);
        applicationService.delete(dummy);
    }

    @GetMapping("/developer/{id}")
    List<Application> allByDev(@PathVariable Long id) {
        return applicationService.getAllApplicationsByDeveloper(id);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
