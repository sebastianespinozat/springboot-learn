package com.example.springbooterror.controllers;

import com.example.springbooterror.models.domain.User;
import com.example.springbooterror.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    public String index(){
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable Long id){
        User user = service.findById(id);
        System.out.println(user.getLastname());
        return user;
    }
}
