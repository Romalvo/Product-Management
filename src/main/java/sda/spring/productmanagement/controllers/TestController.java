package sda.spring.productmanagement.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.spring.productmanagement.entities.ProductEntity;
import sda.spring.productmanagement.repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/environment")
public class TestController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final ProductRepository productRepository;

    public TestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> listAllProducts() {
        if(activeProfile.equals("local")) {
            return productRepository.findAll();
        }else {
            return List.of();
        }
    }
}
