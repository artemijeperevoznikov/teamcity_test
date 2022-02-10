package com.gmail.aperavoznikau.demo.controller.validator;

import com.gmail.aperavoznikau.demo.service.model.AddressDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAddressValidator implements ConstraintValidator<ValidAddress, AddressDTO> {
    @Override
    public boolean isValid(
            AddressDTO addressDTO,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return true;
    }
}
