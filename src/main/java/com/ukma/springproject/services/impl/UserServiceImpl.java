package com.ukma.springproject.services.impl;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.services.EmailService;
import com.ukma.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public boolean notifyAdminsByEmail(String message) {
        List<User> admins = userRepository.findAllByRole(User.ADMIN);
        admins.forEach(user -> emailService.sendEmail(user.getEmail(), "", message));
        return true;
    }

    @Override
    public User save(User user) {
        Optional<User> local = userRepository.findByEmail(user.getEmail());
        if (local.isPresent())
            throw new RuntimeException("User with " + user.getEmail() + " email already exists");
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        // TODO: 24.10.2022
        return null;
    }

    @Override
    public User edit(User user) {
        User local = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User with " + user.getEmail() + " email does not exist"));
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User " + id + " does not exist"));
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User " + id + " does not exist"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with " + email + " email does not exist"));
    }

    @Override
    public List<User> findAllUsersByRole(String role) {
        return userRepository.findAllByRole(role);
    }

}
