package com.example.catalog.adapters.in.graphql.resolver;

import com.example.catalog.adapters.in.graphql.dto.output.CatalogReportResponse;
import com.example.catalog.adapters.in.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.in.graphql.mapper.CatalogReportMapper;
import com.example.catalog.adapters.in.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.in.graphql.mapper.ProductMapper;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.service.GetCatalogReportUseCase;
import com.example.catalog.service.GetProductUseCase;
import com.example.catalog.service.ListProductsUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    private final GetProductUseCase getProduct;
    private final ListProductsUseCase listProducts;
    private final GetCatalogReportUseCase catalogReport;
    private final CategoryRepository categoryRepo;

    public QueryResolver(
            GetProductUseCase getProduct,
            ListProductsUseCase listProducts,
            GetCatalogReportUseCase catalogReport,
            CategoryRepository categoryRepo
    ) {
        this.getProduct = getProduct;
        this.listProducts = listProducts;
        this.catalogReport = catalogReport;
        this.categoryRepo = categoryRepo;
    }

    @QueryMapping
    public ProductResponse product(@Argument("id") String id) {
        return ProductMapper.toResponse(getProduct.execute(id));
    }

    @QueryMapping
    public List<ProductResponse> products() {
        return listProducts.execute().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @QueryMapping
    public List<CategoryResponse> categories() {
        return categoryRepo.findAll().stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @QueryMapping
    public CatalogReportResponse catalogReport() {
        return CatalogReportMapper.toResponse(catalogReport.execute());
    }
}
