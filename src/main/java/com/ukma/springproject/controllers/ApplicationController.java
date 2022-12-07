package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.service.impl.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final GenreService genreService;

    @Autowired
    public ApplicationController(ApplicationService applicationService,
                                 GenreService genreService) {
        this.applicationService = applicationService;
        this.genreService = genreService;
    }

    @GetMapping("create")
    String showApplicationForm(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "application";
    }

    @GetMapping("all")
    List<Application> getAllApps() {
        return applicationService.getAllApplications();
    }

    @GetMapping("{id}")
    Application getAppById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @GetMapping()
    String showApplications() {
        return "applications";
    }

    @PostMapping("create")
    String createApplication(@ModelAttribute("app") Application application, Errors errors) {
//        if (errors.hasErrors()) ;
        System.out.println(application.getName());
        applicationService.create(application);
        return "application";
    }

    @ModelAttribute(value = "app")
    public Application newApplication() {
        return new Application();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
