package com.gmail.aperavoznikau.demo.service.model;

import com.gmail.aperavoznikau.demo.controller.validator.UniqueName;
import com.gmail.aperavoznikau.demo.controller.validator.ValidAddress;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private Long id;

    @NotNull(message = "Please provide a name")
    @Size(min = 2, max = 10, message = "Please provide name length from 2 till 10")
    @UniqueName
    private String name;
}


