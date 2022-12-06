package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Product;
import com.ukma.springproject.service.CategoryService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;
    private final GenreService genreService;
    private final CategoryService categoryService;

    @Autowired
    public ProductsController(
            ProductService productService,
            GenreService genreService,
            CategoryService categoryService) {
        this.productService = productService;
        this.genreService = genreService;
        this.categoryService = categoryService;
    }

    @GetMapping
    String products(Model model) {
        model.addAttribute("products", productService.findAll());
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
