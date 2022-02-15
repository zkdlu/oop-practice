package com.zkdlu.oop.domain.order;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderOption {
    private String name;
    private int price;

    @Builder
    public OrderOption(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
