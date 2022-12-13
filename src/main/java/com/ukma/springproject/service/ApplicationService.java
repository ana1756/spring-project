package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService {

    void create(ApplicationDTO application);
    void delete(Long id);
    ApplicationDTO findById(Long id);
    List<ApplicationDTO> findAll();
    List<ApplicationDTO> findByDeveloper(Long id);
    List<ApplicationDTO> getAllApplications();

}
