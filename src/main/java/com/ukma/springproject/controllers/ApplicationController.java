package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.*;
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

import java.util.List;
import java.util.stream.Collectors;

import static com.ukma.springproject.domain.Role.ROLE_ADMIN;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final GenreService genreService;
    private final ProductService productService;
    private final DBUserDetailsService userDetailsService;
    private final CategoryService categoryService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationController(ApplicationService applicationService, GenreService genreService,
                                 ProductService productService, DBUserDetailsService userDetailsService,
                                 CategoryService categoryService, EmailService emailService,
                                 UserRepository userRepository) {
        this.applicationService = applicationService;
        this.genreService = genreService;
        this.productService = productService;
        this.userDetailsService = userDetailsService;
        this.categoryService = categoryService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    /* developer creates application */
    @GetMapping("create")
    String showApplicationForm(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "application";
    }

    /* developer creates application */
    @PostMapping("create")
    String createApplication(@ModelAttribute("app") Application application, Errors errors) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User dev = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        applicationService.create(application, dev);

        List <String> allAdminsEmailsToNotify = userRepository.readAllByRole(ROLE_ADMIN).
                stream().map(User::getEmail).collect(Collectors.toList());
        emailService.sendAfterApplicationCreation(application, allAdminsEmailsToNotify);
        return "redirect:/applications/my";
    }

    @PostMapping("/publish")
    String publishApplication(@RequestParam(name = "applicationId") Long id,
                              @RequestParam(name = "category") String category) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        Application app = applicationService.findById(id);
        Category cat = categoryService.find(category);
        productService.createFromApplication(app, user, cat);
        emailService.sendAfterApplicationPublishing(app);
        return "redirect:/applications";
    }


    @GetMapping("/my")
    String showDevApplications(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User dev = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        List<Application> apps = applicationService.findByDeveloper(dev.getId());
        model.addAttribute("applications", apps);
        model.addAttribute("genres", genreService.findAll());
        return "applications";
    }

    @GetMapping(value = "/my", params = {"genre", "sorting"})
    String showDevApplications(Model model,
                               @RequestParam String genre,
                               @RequestParam String sorting) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User dev = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        List<Application> apps = applicationService.findByDeveloperSortedAndFiltered(dev.getId(), genre, sorting);
        model.addAttribute("applications", apps);
        model.addAttribute("genres", genreService.findAll());
        return "applications";
    }

    @GetMapping("{id}")
    Application getAppById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @GetMapping()
    String showApplications(Model model) {
        model.addAttribute("applications", applicationService.findAllByPublished(false));
        model.addAttribute("catrgories", categoryService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "applications";
    }

    @GetMapping(params = {"genre", "sorting"})
    String showApplications(Model model,
                            @RequestParam String genre,
                            @RequestParam String sorting) {
        model.addAttribute("applications", applicationService.getAllSortedAndFiltered(genre, sorting));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("catrgories", categoryService.findAll());
        return "applications";
    }

    @ModelAttribute(value = "app")
    public Application newApplication() {
        return new Application();
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    ResponseEntity<String> handleApplicationException(ApplicationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
