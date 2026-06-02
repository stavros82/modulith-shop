package com.example.catalog.service;


import com.example.catalog.exception.CategoryNotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ProductRepository;

import java.math.BigDecimal;

public class UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public UpdateProductUseCase(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product execute(String id, String name, String description, BigDecimal price, String categoryId) {
        if (categoryId != null && categoryRepository.findById(categoryId).isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));

        product.update(name, description, price, categoryId);

        return productRepository.save(product);
    }
}
