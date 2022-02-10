package com.gmail.aperavoznikau.demo.controller;

import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/users", consumes = "application/json")
    public List<UserDTO> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return users;
    }

    @GetMapping("/api/users/{id}")
    public UserDTO getUsers(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/api/users")
    public UserDTO addUser(@RequestBody @Validated UserDTO user) {
        return userService.add(user);
    }


}


