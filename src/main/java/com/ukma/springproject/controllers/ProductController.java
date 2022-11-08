package com.ukma.springproject.controllers;

import com.ukma.springproject.domain.Comment;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.services.ApplicationService;
import com.ukma.springproject.services.ProductService;
import com.ukma.springproject.services.exceptions.CommentNotFoundException;
import com.ukma.springproject.services.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
    private final ApplicationService applicationService;

    ProductController(ProductService productService, ApplicationService applicationService) {
        this.productService = productService;
        this.applicationService = applicationService;
    }

    @PostMapping()
    Product createProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        var dummy = productService.findById(id);
        applicationService.delete(dummy.getApplication());
        productService.delete(dummy);
    }

    @GetMapping("/{id}")
    Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/all")
    List<Product> getAllByUserId() {
        return productService.findAll();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleApplicationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleApplicationException(CommentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
