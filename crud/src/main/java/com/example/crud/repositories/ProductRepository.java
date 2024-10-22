package com.example.crud.repositories;

import com.example.crud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
}
