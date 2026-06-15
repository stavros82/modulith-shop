package com.example.orders.adapters.events;

import com.example.inventory.event.StockNotAvailableEvent;
import com.example.inventory.event.StockReservedEvent;
import com.example.orders.service.MarkOrderNotAvailableUseCase;
import com.example.orders.service.MarkOrderReservedUseCase;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class InventoryEventListener {

    private final MarkOrderReservedUseCase markOrderReservedUseCase;
    private final MarkOrderNotAvailableUseCase markOrderNotAvailableUseCase;

    public InventoryEventListener(
            MarkOrderReservedUseCase markOrderReservedUseCase,
            MarkOrderNotAvailableUseCase markOrderNotAvailableUseCase
    ) {
        this.markOrderReservedUseCase = markOrderReservedUseCase;
        this.markOrderNotAvailableUseCase = markOrderNotAvailableUseCase;
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // 2. Tells Spring to execute this logic in a separate background thread
    @Async
    public void on(StockReservedEvent event) {
        markOrderReservedUseCase.execute(event.orderId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // 2. Tells Spring to execute this logic in a separate background thread
    @Async
    public void on(StockNotAvailableEvent event) {
        markOrderNotAvailableUseCase.execute(event.orderId());
    }
}

