package com.sebastian.springboot.di.app.springboot_di.services;

import com.sebastian.springboot.di.app.springboot_di.models.Product;
import com.sebastian.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{

    private Environment environment;

    private ProductRepository repository;

    public ProductServiceImp(@Qualifier("productRepositoryJson") ProductRepository repository, Environment environment) {
        this.environment = environment;
        this.repository = repository;
    }


    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p->{
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
//            Product newProd = new Product(p.getId(), p.getName(), priceImp.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
//            p.setPrice(priceTax.longValue());
//            return p;

        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }

}
