package com.ukma.springproject.services;

import com.ukma.springproject.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User login(String email, String password);
    User edit(User user);
    void delete(Long id);
    User findById(Long userId);
    User findByEmail(String email);
    List<User> findAllClients();
    List<User> findAllAdmins();
    List<User> findAllDevelopers();
}
