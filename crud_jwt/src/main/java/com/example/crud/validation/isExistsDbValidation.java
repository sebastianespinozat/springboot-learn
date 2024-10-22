package com.example.crud.validation;

import com.example.crud.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class isExistsDbValidation implements ConstraintValidator<isExistsDb, String> {

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(service == null) {
            return true;
        }


        return !service.existsBySky(value);
    }
}
