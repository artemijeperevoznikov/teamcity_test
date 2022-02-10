package com.gmail.aperavoznikau.demo.service;

import com.gmail.aperavoznikau.demo.service.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUser(Long id);

    UserDTO add(UserDTO user);

    boolean isUniqueUsername(String value);
}
