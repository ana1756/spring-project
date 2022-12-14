package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.UserPrincipal;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    String showApplications() {
        return "applications";
    }

    @PostMapping("create")
    String createApplication(@ModelAttribute("app") ApplicationDTO application, Errors errors) {
        var context = SecurityContextHolder.getContext();
        var user = (UserPrincipal) context.getAuthentication().getPrincipal();
        applicationService.create(application, user);
        return "application";
    }

    @ModelAttribute(value = "app")
    public ApplicationDTO newApplication() {
        return new ApplicationDTO();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
