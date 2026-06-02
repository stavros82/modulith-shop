package com.example.catalog.adapters.persistence.mapper;



import com.example.catalog.adapters.persistence.entity.ProductJpaEntity;
import com.example.catalog.model.Product;



public class ProductPersistenceMapper {

    private ProductPersistenceMapper() {
        /* This utility class should not be instantiated */
    }


    public static Product toDomain(ProductJpaEntity e) {
        if (e == null) return null;

        return new Product(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getPrice(),
                e.getCategory() != null ? e.getCategory().getId() : null
        );




    }

    public static ProductJpaEntity toEntity(Product p) {
        if (p == null) return null;

        ProductJpaEntity e = new ProductJpaEntity();
        e.setId(p.id());
        e.setName(p.name());
        e.setDescription(p.description());
        e.setPrice(p.price());

        // category is set in repository impl (because we need CategoryEntity)
        // reviews are saved separately

        return e;
    }
}
