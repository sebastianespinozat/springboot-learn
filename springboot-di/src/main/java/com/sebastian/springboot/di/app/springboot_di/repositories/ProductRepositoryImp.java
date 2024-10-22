package com.sebastian.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.sebastian.springboot.di.app.springboot_di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;


//@RequestScope
@Primary
@Repository("productList")
public class ProductRepositoryImp implements ProductRepository{

    private List<Product> data;

    public ProductRepositoryImp(){
        this.data = Arrays.asList(
                new Product(1L, "Teclado", 10000L),
                new Product(2L, "Mouse", 5000L),
                new Product(3L, "Gabinete", 10000L),
                new Product(4L, "Memoria", 10000L)
        );
    }

    @Override
    public List<Product> findAll(){
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p->p.getId().equals(id)).findFirst().orElseThrow();
    }
}
