package com.example.catalog.service;


import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;

import java.math.BigDecimal;

public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(String id, String name, String description, BigDecimal price, String categoryId) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));

        product.update(name, description, price, categoryId);

        return productRepository.save(product);
    }
}
