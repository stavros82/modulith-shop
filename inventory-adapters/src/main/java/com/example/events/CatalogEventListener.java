package com.example.events;

import com.example.catalog.event.ProductCreatedEvent;
import com.example.catalog.event.ProductUpdatedEvent;
import com.example.catalog.event.QualityIssueReportedEvent;
import com.example.inventory.service.QuarantineProductOnQualityIssueUseCase;
import com.example.inventory.service.ReplenishStockUseCase;
import com.example.inventory.service.SyncProductCreatedUseCase;
import com.example.inventory.service.SyncProductUpdatedUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

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

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // 2. Tells Spring to execute this logic in a separate background thread
    @Async
    public void on(ProductCreatedEvent event) {
        syncProductCreatedUseCase.execute(event.productId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // 2. Tells Spring to execute this logic in a separate background thread
    @Async
    public void on(ProductUpdatedEvent event) {
        syncProductUpdatedUseCase.execute(event.productId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // 2. Tells Spring to execute this logic in a separate background thread
    @Async
    public void on(QualityIssueReportedEvent event) {
        quarantineProductOnQualityIssueUseCase.execute(event.productId());
    }
}

