package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    private final ModelMapper mapper;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void create(ApplicationDTO application) {
        repository.save(mapper.map(application, Application.class));
    }

    @Override
    public void delete(Long id) {
        repository.delete(mapper.map(findById(id), Application.class));
    }

    @Override
    public ApplicationDTO findById(Long id) {
        var result = repository.findById(id).orElseThrow(()
                -> new ApplicationNotFoundException("Application by requested id does not exist"));
        return mapper.map(result, ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> findAll() {
        List<Application> applications = new ArrayList<>();
        repository.findAll().forEach(applications::add);
        return applications.stream()
                .map(x -> mapper.map(x, ApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> findByDeveloper(Long id) {
        return repository.readAllByDeveloperId(id)
                .stream()
                .map(x -> mapper.map(x, ApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return findAll();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
