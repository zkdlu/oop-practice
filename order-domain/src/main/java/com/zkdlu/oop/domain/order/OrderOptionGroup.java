package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.IOptionGroup;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public IOptionGroup convertToOptionGroup() {
        return new IOptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(Collectors.toList()));
    }
}
