package com.ukma.springproject.repositories;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> readByEmail(String email);

    List<User> readAllByRole(Role role);

    void deleteByEmail(String email);
}
