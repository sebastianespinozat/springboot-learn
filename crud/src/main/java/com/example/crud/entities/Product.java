package com.example.crud.entities;

import com.example.crud.validation.isExistsDb;
import com.example.crud.validation.isRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @isRequired
    @isExistsDb
    private String sku;

    @isRequired(message = "{isRequired.product.name}")
    @Size(min=3, max=20)
    private String name;
    @Min(value = 100, message = "{Min.product.price}")
    @NotNull(message = "{NotNull.product.price}")
    private Integer price;
//    @NotBlank(message = "{NotBlank.product.description}")
    @isRequired
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
