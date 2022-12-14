package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.exceptions.BadRequestException;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public void register(User user) {
        User byUserName = repository.readByUsername(user.getUsername());
        User byEmail = repository.readByEmail(user.getEmail());
        if (byUserName != null || byEmail != null)
            throw new BadRequestException("Username or email taken!");
        if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().isEmpty())
            throw new BadRequestException("Password cannot be empty!");
        repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return repository.readByEmail(email);
    }

    @Override
    public List<User> findByRole(Role role) {
        return repository.readAllByRole(role);
    }


}
