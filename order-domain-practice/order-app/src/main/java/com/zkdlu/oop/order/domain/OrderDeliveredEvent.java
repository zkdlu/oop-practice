package com.zkdlu.oop.order.domain;

import lombok.Getter;

@Getter
public class OrderDeliveredEvent {
    private Order order;
    public OrderDeliveredEvent(Order order) {
        this.order = order;
    }
}
