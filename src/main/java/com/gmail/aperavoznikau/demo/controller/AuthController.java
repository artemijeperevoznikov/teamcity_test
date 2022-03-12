package com.gmail.aperavoznikau.demo.controller;

import com.gmail.aperavoznikau.demo.controller.model.JwtResponse;
import com.gmail.aperavoznikau.demo.controller.security.util.JwtUtils;
import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.LoginDTO;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import com.gmail.aperavoznikau.demo.service.model.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        boolean isValidUser = userService.isValidUser(loginDTO.getUsername(), loginDTO.getPassword());
        if (isValidUser) {

            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            Collection<? extends GrantedAuthority> authorities = authentication
                    .getAuthorities();



            String jwt = jwtUtils.generateJwtToken(loginDTO.getUsername());
            UserDTO user = userService.findByUsername(loginDTO.getUsername());
            return ResponseEntity.ok(
                    new JwtResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getRole().name(),
                            jwt
                    )
            );
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Login or password is not valid"));
        }

    }
}

