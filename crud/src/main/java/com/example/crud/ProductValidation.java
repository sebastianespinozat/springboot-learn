package com.example.crud;

import com.example.crud.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        // error corresponde al binding error
        // error code corresponde a la llave
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"Es requerido");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.product.description");
        if(product.getDescription() == null || product.getDescription().isBlank()){
            errors.rejectValue("description", null, "no puede ser nulo!");
        }
        if(product.getPrice() == null){
            errors.rejectValue("price", null, "No puede ser nulo po!");
        }
        else if (product.getPrice() < 100){
            errors.rejectValue("price", null, "Debe ser un valor mayor a 100!");
        }
    }
}
