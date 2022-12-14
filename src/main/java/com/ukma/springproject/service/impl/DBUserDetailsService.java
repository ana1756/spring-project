package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.UserPrincipal;
import com.ukma.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    public DBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        var userOpt = userRepository.readByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        var user = userOpt.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> user.getRole().name());

        return new UserPrincipal(user, user.getEmail(), user.getPassword(), authorities);
    }



}

