package com.proyecto1.springboot_web.controllers;

import com.proyecto1.springboot_web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Sebastian", "Espinoza");
        user.setEmail("example@java.com");
        model.addAttribute("title", "Hola mundo Spring Boot");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model){


//        model.addAttribute("users", users);
        model.addAttribute("title", "Listado de usuarios!");
        return "list";
    }

    @ModelAttribute("users")
    public  List<User> usersModel(){
        List<User> users = Arrays.asList(
                new User("Sebastian", "Espinoza", "ejemplo@ejemplo.com"),
                new User("Pedro", "Espinoza"),
                new User("Juan", "Espinoza"));

        return users;
    }

}
