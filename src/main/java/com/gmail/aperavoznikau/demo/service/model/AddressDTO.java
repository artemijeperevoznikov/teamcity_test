package com.gmail.aperavoznikau.demo.service.model;


import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AddressDTO {

    @Size(max = 200, message = "")
    private String address;
}
