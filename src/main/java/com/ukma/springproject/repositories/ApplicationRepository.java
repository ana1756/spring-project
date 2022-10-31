package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    List<Application> findApplicationByDeveloper(User user);
}
