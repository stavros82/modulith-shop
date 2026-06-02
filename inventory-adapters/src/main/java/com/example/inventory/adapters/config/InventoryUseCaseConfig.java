package com.example.inventory.adapters.config;

import com.example.inventory.repository.InventoryProductRepository;
import com.example.inventory.service.GetInventoryProductUseCase;
import com.example.inventory.service.QuarantineProductOnQualityIssueUseCase;
import com.example.inventory.service.ReserveStockForOrderUseCase;
import com.example.inventory.service.ReplenishStockUseCase;
import com.example.inventory.service.SyncProductCreatedUseCase;
import com.example.inventory.service.SyncProductUpdatedUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryUseCaseConfig {

    @Bean
    SyncProductCreatedUseCase syncProductCreatedUseCase(InventoryProductRepository repository) {
        return new SyncProductCreatedUseCase(repository);
    }

    @Bean
    SyncProductUpdatedUseCase syncProductUpdatedUseCase(InventoryProductRepository repository) {
        return new SyncProductUpdatedUseCase(repository);
    }

    @Bean
    QuarantineProductOnQualityIssueUseCase quarantineProductOnQualityIssueUseCase(InventoryProductRepository repository) {
        return new QuarantineProductOnQualityIssueUseCase(repository);
    }

    @Bean
    ReplenishStockUseCase replenishStockUseCase(InventoryProductRepository repository) {
        return new ReplenishStockUseCase(repository);
    }

    @Bean
    GetInventoryProductUseCase getInventoryProductUseCase(InventoryProductRepository repository) {
        return new GetInventoryProductUseCase(repository);
    }

    @Bean
    ReserveStockForOrderUseCase reserveStockForOrderUseCase(InventoryProductRepository repository) {
        return new ReserveStockForOrderUseCase(repository);
    }
}

