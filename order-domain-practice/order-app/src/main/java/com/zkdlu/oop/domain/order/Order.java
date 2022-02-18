package com.zkdlu.oop.domain.order;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private Long id;
    private Long shop;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();
    private OrderState state;

    @Builder
    public Order(Long id, Long shop, List<OrderLineItem> orderLineItems, OrderState state) {
        this.id = id;
        this.shop = shop;
        this.orderLineItems.addAll(orderLineItems);
        this.state = state;
    }

    public Order(Long shopId, List<OrderLineItem> orderLineItems) {
        this(null, shopId, orderLineItems, OrderState.NONE);
    }

    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
        ordered();
    }

    private void ordered() {
        this.state = OrderState.ORDERED;
    }

    public int calculateTotalPrice() {
        return orderLineItems.stream()
                .mapToInt(OrderLineItem::calculateTotalPrice)
                .sum();
    }

    public void payed() {
        this.state = OrderState.PAYED;
    }

    public void complete() {
        this.state = OrderState.COMPLETE;
    }
}
