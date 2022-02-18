package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.IOptionGroup;
import com.zkdlu.oop.domain.shop.Menu;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderLineItem {
    private Long id;
    private String name;
    private int count;
    private Menu menu;
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    public OrderLineItem(String name, int count, Menu menu, List<OrderOptionGroup> orderOptionGroups) {
        this(null, name, count, menu, orderOptionGroups);
    }

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

    public void validate() {
        menu.validateOrderItem(name, convertToOptionGroups());
    }

    private List<IOptionGroup> convertToOptionGroups() {
        return orderOptionGroups.stream().map(OrderOptionGroup::convertToOptionGroup).collect(Collectors.toList());
    }
}
