package com.ukma.springproject.services;

import com.ukma.springproject.domain.User;

public interface UserService {
    void insert(User user);
    void update(int userId, User user);
    void delete(int userId);

    User findById(int userId);
    User findByName(String firstAndLastname);
}
