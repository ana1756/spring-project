package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showRegistration() {
        return "sign-up";
    }

    @PostMapping
    String processRegistration(@ModelAttribute("user") @Valid User user,
                               Errors errors) {
        if (errors.hasErrors()) return "sign-up";
        userService.register(user);
        return "sign-in";
    }

    @ModelAttribute(value = "user")
    public User newUser() {
        return new User();
    }

}
