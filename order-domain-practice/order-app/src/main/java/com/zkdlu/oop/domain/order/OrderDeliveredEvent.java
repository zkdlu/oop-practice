package com.zkdlu.oop.domain.order;

import lombok.Getter;

@Getter
public class OrderDeliveredEvent {
    private Order order;
    public OrderDeliveredEvent(Order order) {
        this.order = order;
    }
}
