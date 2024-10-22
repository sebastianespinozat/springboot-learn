package com.sebastian.springboot.di.app.springboot_di;

import com.sebastian.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.sebastian.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}
