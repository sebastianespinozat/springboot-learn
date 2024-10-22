package com.proyecto1.springboot_web.controllers;

import com.proyecto1.springboot_web.models.User;
import com.proyecto1.springboot_web.models.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDto details(){
        User user = new User("Sebastian", "Espinozaa");

        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola mundo Spring Boot");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list(){
        User user1 = new User("Sebastian", "Espinoza");
        User user2 = new User("John", "Doe");
        User user3 = new User("Pedro", "Perales");

        List<User> users = Arrays.asList(user1, user2, user3);
//        List<User> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);

        return users;
    }

}
//    @GetMapping("/details")
//    public Map<String, Object> details(){
//        User user = new User("Sebastian", "Espinoza");
//        Map<String, Object> body = new HashMap<>();
//
//        body.put("title", "Hola mundo Spring Boot");
//        body.put("user", user);
//        return body;
//        }