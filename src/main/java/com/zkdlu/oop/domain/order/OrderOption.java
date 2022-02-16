package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.IOption;
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

    public IOption convertToOption() {
        return new IOption(name, price);
    }
}
