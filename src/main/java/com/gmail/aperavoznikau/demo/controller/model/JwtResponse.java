package com.gmail.aperavoznikau.demo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private String role;
    private String jwt;
}
