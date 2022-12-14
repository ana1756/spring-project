package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.CategoryService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.service.KeyService;
import com.ukma.springproject.service.ProductService;
import com.ukma.springproject.service.impl.DBUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;
    private final GenreService genreService;
    private final CategoryService categoryService;
    private final DBUserDetailsService userDetailsService;
    private final KeyService keyService;

    @Autowired
    public ProductsController(
            ProductService productService,
            GenreService genreService,
            CategoryService categoryService, DBUserDetailsService userDetailsService, KeyService keyService) {
        this.productService = productService;
        this.genreService = genreService;
        this.categoryService = categoryService;
        this.userDetailsService = userDetailsService;
        this.keyService = keyService;
    }


    @PostMapping("/buy")
    String buyProduct(@RequestParam(name = "productId") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailsService.loadUserByUsername(auth.getName()).getUser();
        Product p = productService.findById(id);
        keyService.createFromProduct(p, user);
        return "redirect:/profile/keys";
    }

    @ModelAttribute(value = "key")
    public Key newUser() {
        return new Key();
    }

    @GetMapping
    String products(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "products";

    }

    @GetMapping(params = {"category", "genre", "sorting"})
    String products(Model model,
                    @RequestParam String category,
                    @RequestParam String genre,
                    @RequestParam String sorting) {
        System.out.println("Request with params sent");
        model.addAttribute("products", productService.getAllSortedAndFiltered(category, genre, sorting));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    String product(@PathVariable Long id, Model model) {
        Product p = productService.findById(id);
        model.addAttribute("product", p);
        model.addAttribute("comments", p.getComments());
        return "product";
    }
}
