package com.example.springbooterror.services;

import com.example.springbooterror.models.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private List<User> users;
    public UserServiceImp() {
        this.users = new ArrayList<>();
        users.add(new User(1L, "Pepe", "Gonzalez"));
        users.add(new User(2L, "Sebastian", "Gonzalez"));
        users.add(new User(3L, "Sebastian", "Espinoza"));
        users.add(new User(4L, "Jorge", "Gonzalez"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        for (User u:users){
            if(u.getId().equals(id)){
                user = u;
                break;
            }
        }
        return user;
    }
}
