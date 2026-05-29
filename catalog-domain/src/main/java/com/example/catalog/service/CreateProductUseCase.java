package com.example.catalog.service;


import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(String name, String description, BigDecimal price, String categoryId) {
        Product product = new Product(
                UUID.randomUUID().toString(),
                name,
                description,
                price,
                categoryId
        );

        return productRepository.save(product);
    }
}
