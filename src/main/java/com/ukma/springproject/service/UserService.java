package com.ukma.springproject.service;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import java.util.List;

public interface UserService {

    void create(User user);
    void update(Long id, User user);
    void delete(Long id);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findByRole(Role role);

}
