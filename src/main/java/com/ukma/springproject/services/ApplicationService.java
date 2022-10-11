package com.ukma.springproject.services;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.repository.abstractions.ApplicationDao;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    boolean apply(Application application);

    boolean approve(Application application);

    boolean deny(Application application);
}
