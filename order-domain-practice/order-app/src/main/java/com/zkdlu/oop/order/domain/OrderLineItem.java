package com.zkdlu.oop.order.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderLineItem {
    private Long id;
    private String name;
    private int count;
    private Long menu;
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    public OrderLineItem(String name, int count, Long menu, List<OrderOptionGroup> orderOptionGroups) {
        this(null, name, count, menu, orderOptionGroups);
    }

    @Builder
    public OrderLineItem(Long id, String name, int count, Long menu, List<OrderOptionGroup> orderOptionGroups) {
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
