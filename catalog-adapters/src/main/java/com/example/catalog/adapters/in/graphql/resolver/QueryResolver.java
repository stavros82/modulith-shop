package com.example.catalog.adapters.in.graphql.resolver;

import com.example.catalog.adapters.in.graphql.dto.output.CatalogReportResponse;
import com.example.catalog.adapters.in.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.in.graphql.mapper.CatalogReportMapper;
import com.example.catalog.adapters.in.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.in.graphql.mapper.ProductMapper;
import com.example.catalog.adapters.in.graphql.service.CatalogQueryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    private final CatalogQueryService catalogQueryService;

    public QueryResolver(CatalogQueryService catalogQueryService) {
        this.catalogQueryService = catalogQueryService;
    }

    @QueryMapping
    public ProductResponse product(@Argument("id") String id) {
        return ProductMapper.toResponse(catalogQueryService.getProduct(id));
    }

    @QueryMapping
    public List<ProductResponse> products() {
        return catalogQueryService.listProducts().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @QueryMapping
    public List<CategoryResponse> categories() {
        return catalogQueryService.listAllCategories().stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @QueryMapping
    public CatalogReportResponse catalogReport() {
        return CatalogReportMapper.toResponse(catalogQueryService.getCatalogReport());
    }
}
