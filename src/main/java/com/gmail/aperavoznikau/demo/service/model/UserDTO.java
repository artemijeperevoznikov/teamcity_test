package com.gmail.aperavoznikau.demo.service.model;

import com.gmail.aperavoznikau.demo.repository.model.RoleEnum;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private RoleEnum role;
}


