package com.gmail.aperavoznikau.demo.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAddressValidator.class)
public @interface ValidAddress {
    String message() default "Address is not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


