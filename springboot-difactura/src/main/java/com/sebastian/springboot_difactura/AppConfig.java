package com.sebastian.springboot_difactura;

import com.sebastian.springboot_difactura.models.Item;
import com.sebastian.springboot_difactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:text.properties")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){
        Product p1 = new Product("Camara Sony", 1000);
        Product p2 = new Product("Bicicleta", 300);

        List<Item> items = Arrays.asList(new Item(p1,2), new Item(p2,4));
        return items;
    }

    @Bean
    @Primary
    List<Item> itemsInvoiceOffice(){
        Product p1 = new Product("Monitor", 1200);
        Product p2 = new Product("Impresora", 300);

        List<Item> items = Arrays.asList(new Item(p1,5), new Item(p2,2));
        return items;
    }

}
