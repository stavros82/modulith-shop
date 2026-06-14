package com.example.catalog.adapters.in.graphql.service;

import com.example.catalog.model.Category;
import com.example.catalog.model.Review;
import com.example.catalog.model.Product;
import com.example.catalog.model.CatalogReport;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ReviewRepository;
import com.example.catalog.service.GetProductUseCase;
import com.example.catalog.service.ListProductsUseCase;
import com.example.catalog.service.GetCatalogReportUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogQueryService {

    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final GetProductUseCase getProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final GetCatalogReportUseCase getCatalogReportUseCase;

    public CatalogQueryService(CategoryRepository categoryRepository,
                               ReviewRepository reviewRepository,
                               GetProductUseCase getProductUseCase,
                               ListProductsUseCase listProductsUseCase,
                               GetCatalogReportUseCase getCatalogReportUseCase) {
        this.categoryRepository = categoryRepository;
        this.reviewRepository = reviewRepository;
        this.getProductUseCase = getProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.getCatalogReportUseCase = getCatalogReportUseCase;
    }

    @Transactional(readOnly = true)
    public Optional<Category> findCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Review> findReviewsByProductId(String productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public Product getProduct(String id) {
        return getProductUseCase.execute(id);
    }

    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return listProductsUseCase.execute();
    }

    @Transactional(readOnly = true)
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CatalogReport getCatalogReport() {
        return getCatalogReportUseCase.execute();
    }
}
