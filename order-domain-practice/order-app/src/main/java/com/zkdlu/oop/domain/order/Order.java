package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Shop;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private Long id;
    private Shop shop;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();
    private OrderState state;

    @Builder
    public Order(Long id, Shop shop, List<OrderLineItem> orderLineItems, OrderState state) {
        this.id = id;
        this.shop = shop;
        this.orderLineItems.addAll(orderLineItems);
        this.state = state;
    }

    public Order(Shop shop, List<OrderLineItem> orderLineItems) {
        this(null, shop, orderLineItems, OrderState.NONE);
    }

    public void place() {
        validate();
        ordered();
    }

    private void ordered() {
        this.state = OrderState.ORDERED;
    }

    private void validate() {
        if (orderLineItems.isEmpty()) {
            throw new IllegalStateException("주문항목이 비어있습니다.");
        }

        if (!shop.isOpen()) {
            throw new IllegalStateException("가게가 영업중이 아닙니다.");
        }

        if (!shop.isValidOrderAmount(calculateTotalPrice())) {
            throw new IllegalStateException(
                    String.format("최소 주문 금액 %s원 이상을 넘겨주세요.", shop.getMinOrderAmount()));
        }

        for (var orderLineItem : this.orderLineItems) {
            orderLineItem.validate();
        }
    }

    private int calculateTotalPrice() {
        return orderLineItems.stream()
                .mapToInt(OrderLineItem::calculateTotalPrice)
                .sum();
    }

    public void payed() {
        this.state = OrderState.PAYED;
    }
}
