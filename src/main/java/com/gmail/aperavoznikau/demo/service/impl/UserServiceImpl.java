package com.gmail.aperavoznikau.demo.service.impl;

import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public List<UserDTO> getUsers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("etewttewte");
        return Collections.singletonList(userDTO);
    }

    @Override
    public UserDTO getUser(Long id) {
        return null;
    }

    @Override
    public UserDTO add(UserDTO user) {
        log.info("User to add {} {}", user, 4);
        return user;
    }

    @Override
    public boolean isUniqueUsername(String value) {
        return true;
    }
}
