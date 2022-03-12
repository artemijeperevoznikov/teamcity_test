package com.gmail.aperavoznikau.demo.service.impl;

import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import com.gmail.aperavoznikau.demo.service.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User was not found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }
}

