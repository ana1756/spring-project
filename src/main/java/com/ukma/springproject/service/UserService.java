package com.ukma.springproject.service;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void register(User user);

    void update(User user);

    void delete(Long id);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findByRole(Role role);

}
