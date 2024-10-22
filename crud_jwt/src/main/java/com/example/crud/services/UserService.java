package com.example.crud.services;

import com.example.crud.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
