package com.example.catalog.adapters.out.persistence.mapper;


import com.example.catalog.adapters.out.persistence.entity.ProductJpaEntity;
import com.example.catalog.adapters.out.persistence.entity.ReviewJpaEntity;
import com.example.catalog.model.Review;

public class ReviewPersistenceMapper {




    public static Review toDomain(ReviewJpaEntity e) {
        if (e == null) return null;

        return new Review(
                e.getId(),
                e.getRating(),
                e.getComment(),
                e.getProduct() != null ? e.getProduct().getId() : null,

                e.getCreatedAt()
        );
    }

    public static ReviewJpaEntity toEntity(Review r) {
        if (r == null) return null;

        ReviewJpaEntity e = new ReviewJpaEntity();
        e.setId(r.id());
        e.setRating(r.rating());
        e.setComment(r.comment());
        e.setCreatedAt(r.createdAt());
            // set only product reference (we only need the id for the FK)
        if (r.productId() != null) {
            ProductJpaEntity product = new ProductJpaEntity();
            product.setId(r.productId());
            e.setProduct(product);
        }
        return e;
    }
}
