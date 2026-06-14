package com.example.catalog.service;


import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.exception.CategoryNotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductEventPublisher eventPublisher;

    public CreateProductUseCase(ProductRepository productRepository, 
                                CategoryRepository categoryRepository,
                                ProductEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    public Product execute(String name, String description, BigDecimal price, String categoryId) {
        requireCategoryExists(categoryId);

        Product product = new Product(
                UUID.randomUUID().toString(),
                name,
                description,
                price,
                categoryId
        );

        Product savedProduct = productRepository.save(product);
        eventPublisher.publishProductCreated(savedProduct);
        return savedProduct;
    }

    private void requireCategoryExists(String categoryId) {
        if (categoryId != null && categoryRepository.findById(categoryId).isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }
    }
}
