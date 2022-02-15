package com.zkdlu.oop.domain.order;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderOptionGroup {
    private Long id;
    private String name;
    private List<OrderOption> orderOptions = new ArrayList<>();

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> orderOptions) {
        this.id = id;
        this.name = name;
        this.orderOptions.addAll(orderOptions);
    }

    public int calculatePrice() {
        return orderOptions.stream()
                .mapToInt(OrderOption::getPrice)
                .sum();
    }
}
