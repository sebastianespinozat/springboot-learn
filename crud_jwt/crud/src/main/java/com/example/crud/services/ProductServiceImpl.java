package com.example.crud.services;

import com.example.crud.entities.Product;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isPresent()){
            Product productDb = productOptional.orElseThrow();
            productDb.setSku(product.getSku());
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setDescription(product.getDescription());
            return Optional.of(repository.save(productDb));
        }
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });
        return productOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySky(String sku) {
        return repository.existsBySku(sku);
    }
}
