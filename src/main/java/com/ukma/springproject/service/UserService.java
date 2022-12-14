package com.ukma.springproject.service;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    void save(UserDTO user);
    void update(UserDTO user);
    void delete(Long id);
    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
    List<UserDTO> findByRole(Role role);

}
