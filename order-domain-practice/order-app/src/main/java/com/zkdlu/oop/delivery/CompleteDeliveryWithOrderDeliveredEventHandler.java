package com.zkdlu.oop.delivery;

import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderDeliveredEvent;
import org.springframework.stereotype.Component;

@Component
public class CompleteDeliveryWithOrderDeliveredEventHandler {
    private final DeliveryRepository deliveryRepository;

    public CompleteDeliveryWithOrderDeliveredEventHandler(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void handle(OrderDeliveredEvent orderDeliveredEvent) {
        Order order = orderDeliveredEvent.getOrder();

        Delivery delivery = deliveryRepository.findById(order.getId()).orElseThrow(IllegalArgumentException::new);;
        delivery.complete();
    }
}
