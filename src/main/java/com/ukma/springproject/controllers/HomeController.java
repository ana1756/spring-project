package com.ukma.springproject.controllers;

import com.ukma.springproject.aspects.LogExecutionTime;
import com.ukma.springproject.aspects.LogParameters;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    @LogExecutionTime
    @LogParameters
    public String home(){
        return "home";
    }
}
