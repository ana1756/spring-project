package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> readAllByDeveloperId(Long id);
    List<Application> readAllByPublished(boolean flag);
    void deleteAllByDeveloperEmail(String email);
}
