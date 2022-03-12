package com.gmail.aperavoznikau.demo.service;

import com.gmail.aperavoznikau.demo.service.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO add(UserDTO user);

    boolean isUniqueUsername(String username);

    UserDTO findByUsername(String username);

    boolean isValidUser(String username, String password);
}
