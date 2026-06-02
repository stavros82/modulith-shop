package com.example.orders.adapters.events;

import com.example.inventory.event.StockNotAvailableEvent;
import com.example.inventory.event.StockReservedEvent;
import com.example.orders.service.MarkOrderNotAvailableUseCase;
import com.example.orders.service.MarkOrderReservedUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

    @EventListener
    public void on(StockReservedEvent event) {
        markOrderReservedUseCase.execute(event.orderId());
    }

    @EventListener
    public void on(StockNotAvailableEvent event) {
        markOrderNotAvailableUseCase.execute(event.orderId());
    }
}

