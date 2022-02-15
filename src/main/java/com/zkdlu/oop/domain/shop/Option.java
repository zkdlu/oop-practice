package com.zkdlu.oop.domain.shop;

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
}
