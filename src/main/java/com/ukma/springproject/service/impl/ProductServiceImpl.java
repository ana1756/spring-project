package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Product;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.exceptions.BadRequestException;
import com.ukma.springproject.exceptions.EntityNotFoundException;
import com.ukma.springproject.repositories.ProductRepository;
import com.ukma.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final Set<String> allowed;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, Set<String> allowed) {
        this.repository = repository;
        this.allowed = allowed;
    }

    @Override
    public void create(Product product) {
        repository.save(product);
    }

    @Override
    public void createFromApplication(Application application, User admin, Category category) {
        if (application.getProduct() == null) {
            Product product = new Product();
            product.setApplication(application);
            product.setDateCreated(new Date());
            product.setAdmin(admin);
            product.setCategory(category);
            application.setPublished(true);
            create(product);
        }
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product by given id could not be found"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        return repository.readAllByCategory_Name(categoryName);
    }

    @Override
    public List<Product> getAllSortedAndFiltered(String category, String genre, String sorting) {
        if (!allowed.contains(sorting))
            throw new BadRequestException("Invalid sorting method");
        return repository.readAllByCategory_Name(category)
                .stream().filter(x ->
                        x.getApplication().getGenres().stream().anyMatch(y -> y.getName().equals(genre)))
                .sorted((o1, o2) -> {
                    var compResult = 0;
                    switch (sorting) {
                        case "lowPrice":
                            compResult = Double.compare(o1.getApplication().getPrice(), o2.getApplication().getPrice());
                            break;
                        case "highPrice":
                            compResult = Double.compare(o2.getApplication().getPrice(), o1.getApplication().getPrice());
                            break;
                        case "aToz":
                            compResult = o1.getApplication().getName().compareTo(o2.getApplication().getName());
                            break;
                        case "zToa":
                            compResult = o2.getApplication().getName().compareTo(o1.getApplication().getName());
                            break;
                        case "oldToNew":
                            compResult = o1.getDateCreated().compareTo(o2.getDateCreated());
                            break;
                        case "newToOld":
                            compResult = o2.getDateCreated().compareTo(o1.getDateCreated());
                            break;
                        default:
                            throw new BadRequestException("Unexpected value: " + sorting);
                    }
                    return compResult;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void buy(User user, Product product) {
        var userBalance = user.getBalance();
        var prodPrice = product.getApplication().getPrice();
        if (userBalance < prodPrice)
            throw new BadRequestException("Your balance is too low!");
        user.setBalance(userBalance - prodPrice);
    }
}
