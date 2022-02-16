package com.zkdlu.oop.domain.shop;

import lombok.Getter;

@Getter
public class IOption {
    private String name;
    private int price;

    public IOption(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
