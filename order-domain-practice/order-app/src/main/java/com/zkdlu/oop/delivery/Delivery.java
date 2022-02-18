package com.zkdlu.oop.delivery;

import com.zkdlu.oop.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Delivery {
    public  enum DeliveryState {
        DELIVERING, DELIVERED
    }

    private Order order;
    private DeliveryState state;

    @Builder
    public Delivery(Order order, DeliveryState state) {
        this.order = order;
        this.state = state;
    }

    public static Delivery started(Order order) {
        return new Delivery(order, DeliveryState.DELIVERING);
    }

    public void complete() {
        this.state = DeliveryState.DELIVERED;
    }
}
