package com.gmail.aperavoznikau.demo.service.model;


import com.gmail.aperavoznikau.demo.controller.validator.ValidAddress;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@ValidAddress
public class AddressDTO {

    @Size(max = 200, message = "")
    private String address;
}
