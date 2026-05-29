package com.example.catalog.adapters.persistence.mapper;

import com.example.catalog.adapters.persistence.entity.ProductJpaEntity;
import com.example.catalog.model.Product;


public class ProductMapper {

    public static ProductJpaEntity toJpa(Product product) {
        return new ProductJpaEntity(
                product.id(),
                product.name(),
                product.price()
        );
    }

    public static Product toDomain(ProductJpaEntity product) {
        return new Product(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
