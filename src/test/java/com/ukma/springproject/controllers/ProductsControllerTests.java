package com.ukma.springproject.controllers;


import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Genre;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.service.CategoryService;
import com.ukma.springproject.service.GenreService;
import com.ukma.springproject.service.ProductService;
import com.ukma.springproject.service.impl.DBUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
class ProductsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommandLineRunner initDatabase;

    @MockBean
    private DBUserDetailsService service;

    @MockBean
    private ProductService productService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getProductsTest() throws Exception {
        Genre g = new Genre();
        List<Genre> allGenres = List.of(g);

        Category c = new Category();
        List<Category> allCategories = List.of(c);

        Application app = new Application();
        app.setName("Application");
        app.setGenres(allGenres);

        Product p = new Product();
        p.setApplication(app);
        List<Product> allProducts = List.of(p);

        given(productService.findAll()).willReturn(allProducts);
        given(genreService.findAll()).willReturn(allGenres);
        given(categoryService.findAll()).willReturn(allCategories);

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", equalTo(allProducts)))
                .andExpect(model().attribute("categories", equalTo(allCategories)))
                .andExpect(model().attribute("genres", equalTo(allGenres)));

    }


}
