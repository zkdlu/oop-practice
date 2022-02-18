package com.zkdlu.oop.delivery;

import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartDeliveryWithOrderPayedEventHandler {
    private final DeliveryRepository deliveryRepository;

    public StartDeliveryWithOrderPayedEventHandler(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void handle(OrderPayedEvent orderPayedEvent) {
        Order order = orderPayedEvent.getOrder();

        Delivery delivery = Delivery.started(order);
        deliveryRepository.save(delivery);
    }
}
