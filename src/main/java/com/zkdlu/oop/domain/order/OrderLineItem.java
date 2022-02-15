package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Menu;
import lombok.Builder;

public class OrderLineItem {
    private Long id;
    private String name;
    private int count;
    private Menu menu;

    @Builder
    public OrderLineItem(Long id, String name, int count, Menu menu) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.menu = menu;
    }
}
