package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.readByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }



}

