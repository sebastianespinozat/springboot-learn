package com.example.crud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<isRequired, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return(value != null && !value.isEmpty() && !value.isBlank());
        //return StringUtils.hasText(value);
    }
}
