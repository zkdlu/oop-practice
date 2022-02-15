package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Shop;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Shop shop;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Builder
    public Order(Long id, Shop shop, List<OrderLineItem> orderLineItems) {
        this.id = id;
        this.shop = shop;
        this.orderLineItems.addAll(orderLineItems);
    }

    public void place() {
        validate();
    }

    private void validate() {
        if (orderLineItems.isEmpty()) {
            throw new IllegalStateException("주문항목이 비어있습니다.");
        }

        if (!shop.isOpen()) {
            throw new IllegalStateException("가게가 영업중이 아닙니다.");
        }
    }
}
