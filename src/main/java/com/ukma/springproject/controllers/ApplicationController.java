package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.domain.UserPrincipal;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.service.CategoryService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.service.ProductService;
import com.ukma.springproject.service.impl.DBUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final GenreService genreService;
    private final ProductService productService;
    private final DBUserDetailsService userDetailsService;
    private final CategoryService categoryService;

    @Autowired
    public ApplicationController(ApplicationService applicationService,
                                 GenreService genreService, ProductService productService, DBUserDetailsService userDetailsService, CategoryService categoryService) {
        this.applicationService = applicationService;
        this.genreService = genreService;
        this.productService = productService;
        this.userDetailsService = userDetailsService;
        this.categoryService = categoryService;
    }



    @GetMapping("create")
    String showApplicationForm(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "application";
    }

    @GetMapping()
    String showApplications(Model model) {
        model.addAttribute("applications", applicationService.findAllByPublished(false));

        return "applications";
    }

//    @PostMapping("create")
//    String createApplication(@ModelAttribute("app") ApplicationDTO application, Errors errors) {
//        var context = SecurityContextHolder.getContext();
//        var user = (UserPrincipal) context.getAuthentication().getPrincipal();
//        applicationService.create(application, user);
//        return "redirect:/applications/my";
//    }

    @PostMapping("create")
    String createApplication(@ModelAttribute("app") Application application, Errors errors) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        application.setDeveloper(user);
        application.setDateCreated(new Date());
        applicationService.create(application);
        return "redirect:/applications/my";
    }

    @PostMapping("/publish")
    String publishApplication(@RequestParam(name = "applicationId") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        Application app = applicationService.findApplicationById(id);
        productService.createFromApplication(app, user, categoryService.find("paid"));
        return "applications";
    }

    @GetMapping("/my")
    String showDevApplications(Model model) {
        var context = SecurityContextHolder.getContext();
        var user = (UserPrincipal) context.getAuthentication().getPrincipal();
       List<ApplicationDTO> apps =  applicationService.findByDeveloper(user.getUser().getId());
       model.addAttribute("applications", apps);
       return "applications";
    }

//    @ModelAttribute(value = "app")
//    public ApplicationDTO newApplication() {
//        return new ApplicationDTO();
//    }

    @ModelAttribute(value = "app")
    public Application newApplication() {
        return new Application();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
