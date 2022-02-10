package com.gmail.aperavoznikau.demo.controller.validator;

import com.gmail.aperavoznikau.demo.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final UserService userService;

    public UniqueNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return userService.isUniqueUsername(value);
    }
}


