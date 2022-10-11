package com.ukma.springproject.repository.abstractions;

import com.ukma.springproject.domain.User;

public interface UserDao {
    void insert(User user);
    void update(int userId, User user);
    void delete(int userId);

    User findById(int userId);
    User findByName(String firstAndLastname);
}
