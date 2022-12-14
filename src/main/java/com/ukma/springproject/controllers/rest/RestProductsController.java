package com.ukma.springproject.controllers.rest;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.domain.dto.ProductDTO;
import com.ukma.springproject.exceptions.ApplicationNotFoundException;
import com.ukma.springproject.exceptions.EntityNotFoundException;
import com.ukma.springproject.service.ApplicationService;
import com.ukma.springproject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/application")
public class RestProductsController {
    private final ProductService productService;

    RestProductsController(ProductService applicationService) {
        this.productService = applicationService;
    }

    @GetMapping("/all")
    List<ProductDTO> all() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/developer/{id}")
    List<ProductDTO> allByDev(@PathVariable Long id) {
        return productService.findByDeveloper(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleApplicationException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleApplicationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}