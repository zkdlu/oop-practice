package com.zkdlu.oop.order.domain;

import lombok.Getter;

@Getter
public class OrderPayedEvent {
    private Order order;

    public OrderPayedEvent(Order order) {
        this.order = order;
    }
}
