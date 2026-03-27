package com.microservice.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.product_service.entity.Product;
import com.microservice.product_service.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository prodrepo;

    // add product request body from header
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return prodrepo.save(product);
    }

    // get all prodcut, it give list of all prodiucts so list
    @GetMapping
    public List<Product> getAllProducts() {
        return prodrepo.findAll();
    }

    // get by id, need to return response so ResponseEntity
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long productId) {

        Product product = prodrepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND WITH ID: " + productId));

        return ResponseEntity.ok(product);
    }

}
