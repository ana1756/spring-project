package com.ukma.springproject.controllers.rest;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import com.ukma.springproject.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/application")
public class RestApplicationController {
    private final ApplicationService applicationService;

    RestApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/all")
    List<ApplicationDTO> all() {
        return applicationService.getAllApplications();
    }

    @PostMapping("/create")
    void createApplication(@Valid @RequestBody ApplicationDTO application) {
        applicationService.create(application);
    }

    @GetMapping("/{id}")
    ApplicationDTO findById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        var dummy = new Application();
        dummy.setId(id);
        applicationService.delete(id);
    }

    @GetMapping("/developer/{id}")
    List<ApplicationDTO> allByDev(@PathVariable Long id) {
        return applicationService.findByDeveloper(id);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleApplicationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}