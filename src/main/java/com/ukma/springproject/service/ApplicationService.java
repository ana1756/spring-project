package com.ukma.springproject.service;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.UserPrincipal;
import com.ukma.springproject.domain.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService {

    void create(ApplicationDTO application);

    // TODO: genre mapping
    void create(Application application);
    List<Application> findAllByPublished(boolean flag);
    Application findApplicationById(Long id);
    //

    void delete(Long id);
    ApplicationDTO findById(Long id);
    List<ApplicationDTO> findAll();
    List<ApplicationDTO> findByDeveloper(Long id);
    List<ApplicationDTO> getAllApplications();

    void create(ApplicationDTO application, UserPrincipal user);
}
