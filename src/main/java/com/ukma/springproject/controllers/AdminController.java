package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.exceptions.UserNotFoundException;
import com.ukma.springproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addAdmin")
    public String addNewAdmin(@RequestParam(name = "email") String email){
        User user = userService.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException(email);
        user.setRole(Role.ROLE_ADMIN);
        userService.update(user);
        return  "redirect:/profile";
    }

    @GetMapping("/addDev")
    public String addNewDev(@RequestParam(name = "email") String email){
        User user = userService.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException(email);
        user.setRole(Role.ROLE_DEV);
        userService.update(user);
        return "redirect:/profile";
    }

}
