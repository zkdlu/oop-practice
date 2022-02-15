package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Menu;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class OrderLineItem {
    private Long id;
    private String name;
    private int count;
    private Menu menu;
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    @Builder
    public OrderLineItem(Long id, String name, int count, Menu menu, List<OrderOptionGroup> orderOptionGroups) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.menu = menu;
        this.orderOptionGroups.addAll(orderOptionGroups);
    }

    public int calculateTotalPrice() {
        return orderOptionGroups.stream()
                .mapToInt(OrderOptionGroup::calculatePrice)
                .sum() * count;
    }
}
