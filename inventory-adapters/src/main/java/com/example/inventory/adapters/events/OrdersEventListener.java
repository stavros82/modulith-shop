package com.example.inventory.adapters.events;

import com.example.inventory.service.ReserveStockForOrderUseCase;
import com.example.shared.OrderCreatedEvent;
import com.example.shared.StockNotAvailableEvent;
import com.example.shared.StockReservedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrdersEventListener {

    private final ReserveStockForOrderUseCase reserveStockForOrderUseCase;
    private final ApplicationEventPublisher eventPublisher;

    public OrdersEventListener(
            ReserveStockForOrderUseCase reserveStockForOrderUseCase,
            ApplicationEventPublisher eventPublisher
    ) {
        this.reserveStockForOrderUseCase = reserveStockForOrderUseCase;
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void on(OrderCreatedEvent event) {
        var result = reserveStockForOrderUseCase.execute(event.orderId(), event.productId(), event.quantity());
        if (result instanceof ReserveStockForOrderUseCase.Reserved reserved) {
            eventPublisher.publishEvent(new StockReservedEvent(reserved.orderId(), reserved.productId(), reserved.quantity()));
            return;
        }
        if (result instanceof ReserveStockForOrderUseCase.NotAvailable notAvailable) {
            eventPublisher.publishEvent(new StockNotAvailableEvent(
                    notAvailable.orderId(),
                    notAvailable.productId(),
                    notAvailable.requested(),
                    notAvailable.available()
            ));
        }
    }
}
