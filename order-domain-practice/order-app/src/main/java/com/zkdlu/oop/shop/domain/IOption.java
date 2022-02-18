package com.zkdlu.oop.shop.domain;

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
