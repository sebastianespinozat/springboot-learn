package com.sebastian.springboot.di.app.springboot_di.repositories;

import com.sebastian.springboot.di.app.springboot_di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


@Repository
public class ProductRepositoryFoo implements ProductRepository{


    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Monitor Asus", 600L);
    }
}
