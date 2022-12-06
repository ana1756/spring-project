package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showProfile(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @ModelAttribute(value = "user")
    public User newUser(){
        return new User();
    }

}
