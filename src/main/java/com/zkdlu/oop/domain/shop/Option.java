package com.zkdlu.oop.domain.shop;

import com.zkdlu.oop.domain.order.OrderOption;
import lombok.Builder;

public class Option {
    private Long id;
    private String name;
    private int price;

    @Builder
    public Option(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private boolean isMatched(OrderOption orderOption) {
        return this.name.equals(orderOption.getName()) && this.price == orderOption.getPrice();
    }

    public boolean isSatisfiedBy(OrderOption orderOption) {
        return isMatched(orderOption);
    }
}
