package com.zkdlu.oop.billing;

import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderDeliveredEvent;
import com.zkdlu.oop.shop.domain.Shop;
import com.zkdlu.oop.shop.domain.ShopRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BillShopWithOrderDeliveredEventHandler {
    private final ShopRepository shopRepository;
    private final BillingRepository billingRepository;

    public BillShopWithOrderDeliveredEventHandler(ShopRepository shopRepository, BillingRepository billingRepository) {
        this.shopRepository = shopRepository;
        this.billingRepository = billingRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void handle(OrderDeliveredEvent orderDeliveredEvent) {
        Order order = orderDeliveredEvent.getOrder();
        Shop shop = shopRepository.findById(order.getShop()).orElseThrow(IllegalArgumentException::new);;
        Billing billing = billingRepository.findByShopId(order.getShop())
                        .orElseGet(() -> new Billing(order.getShop()));

        billing.billCommissionFee(
                shop.calculdateCommissionFee(order.calculateTotalPrice())
        );
    }
}
