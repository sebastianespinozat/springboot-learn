package com.example.springbooterror.services;
import com.example.springbooterror.models.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
}
