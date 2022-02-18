package com.zkdlu.oop.order.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order extends AbstractAggregateRoot<Order> {
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

        registerEvent(new OrderPayedEvent(this));
    }

    public void complete() {
        this.state = OrderState.COMPLETE;

        registerEvent(new OrderDeliveredEvent(this));
    }
}
