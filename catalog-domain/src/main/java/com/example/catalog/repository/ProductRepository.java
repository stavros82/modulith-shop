package com.example.catalog.repository;

import com.example.catalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    List<Product> findAll();

    Product save(Product product);
}
