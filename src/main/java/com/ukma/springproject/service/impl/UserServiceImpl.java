package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.domain.dto.UserDTO;
import com.ukma.springproject.exceptions.EntityNotFoundException;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder, ModelMapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public void save(UserDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(mapper.map(user, User.class));
    }

    @Override
    public void update(UserDTO user) {
        UserDTO idHolder = findByEmail(user.getEmail());
        User user1 = mapper.map(user, User.class);
        if (idHolder != null)
            user1.setId(idHolder.getId());
        repository.save(user1);
    }

    @Override
    public void delete(Long id) {
        repository.delete(mapper.map(findById(id), User.class));
    }

    @Override
    public UserDTO findById(Long id) {
        var res = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User by id was not found"));
        return mapper.map(res, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        var res = repository.readByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("User by id was not found"));
        return mapper.map(res, UserDTO.class);
    }

    @Override
    public List<UserDTO> findByRole(Role role) {
        return repository.readAllByRole(role)
                .stream()
                .map(x -> mapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
    }


}
