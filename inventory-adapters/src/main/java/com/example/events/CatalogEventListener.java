package com.example.events;

import com.example.catalog.event.ProductCreatedEvent;
import com.example.catalog.event.ProductUpdatedEvent;
import com.example.catalog.event.QualityIssueReportedEvent;
import com.example.inventory.service.QuarantineProductOnQualityIssueUseCase;
import com.example.inventory.service.ReplenishStockUseCase;
import com.example.inventory.service.SyncProductCreatedUseCase;
import com.example.inventory.service.SyncProductUpdatedUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CatalogEventListener {

    private final SyncProductCreatedUseCase syncProductCreatedUseCase;
    private final SyncProductUpdatedUseCase syncProductUpdatedUseCase;
    private final QuarantineProductOnQualityIssueUseCase quarantineProductOnQualityIssueUseCase;
    @SuppressWarnings("unused")
    private final ReplenishStockUseCase replenishStockUseCase;

    public CatalogEventListener(
            SyncProductCreatedUseCase syncProductCreatedUseCase,
            SyncProductUpdatedUseCase syncProductUpdatedUseCase,
            QuarantineProductOnQualityIssueUseCase quarantineProductOnQualityIssueUseCase,
            ReplenishStockUseCase replenishStockUseCase
    ) {
        this.syncProductCreatedUseCase = syncProductCreatedUseCase;
        this.syncProductUpdatedUseCase = syncProductUpdatedUseCase;
        this.quarantineProductOnQualityIssueUseCase = quarantineProductOnQualityIssueUseCase;
        this.replenishStockUseCase = replenishStockUseCase;
    }

    @EventListener
    public void on(ProductCreatedEvent event) {
        syncProductCreatedUseCase.execute(event.productId());
    }

    @EventListener
    public void on(ProductUpdatedEvent event) {
        syncProductUpdatedUseCase.execute(event.productId());
    }

    @EventListener
    public void on(QualityIssueReportedEvent event) {
        quarantineProductOnQualityIssueUseCase.execute(event.productId());
    }
}

