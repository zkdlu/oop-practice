package com.zkdlu.oop.domain.shop;

import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderDeliveredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BillShopWithOrderDeliveredEventHandler {
    private final ShopRepository shopRepository;

    public BillShopWithOrderDeliveredEventHandler(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void handle(OrderDeliveredEvent orderDeliveredEvent) {
        Order order = orderDeliveredEvent.getOrder();

        Shop shop = shopRepository.findById(order.getShop()).orElseThrow(IllegalArgumentException::new);;
        shop.billCommissionFee(order.calculateTotalPrice());
    }
}
