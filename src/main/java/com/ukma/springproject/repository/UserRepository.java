package com.ukma.springproject.repository;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.repository.abstractions.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements UserDao {
    @Override
    public void insert(User user) {

    }

    @Override
    public void update(int userId, User user) {

    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public User findById(int userId) {
        return null;
    }

    @Override
    public User findByName(String firstAndLastname) {
        return null;
    }
}
