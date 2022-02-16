package com.zkdlu.oop.domain.shop;

import com.zkdlu.oop.domain.order.OrderOption;
import com.zkdlu.oop.domain.order.OrderOptionGroup;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class OptionGroup {
    private Long id;
    private String name;

    private boolean exclusive;
    private boolean basic;

    private List<Option> options = new ArrayList<>();

    @Builder
    public OptionGroup(Long id, String name, boolean exclusive, boolean basic, List<Option> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;

        this.options.addAll(options);
    }

    public boolean isSatisfiedBy(OrderOptionGroup orderOptionGroup) {
        if (!this.name.equals(orderOptionGroup.getName())) {
            return false;
        }

        return isSatisfiedBy(orderOptionGroup.getOrderOptions());
    }

    private boolean isSatisfiedBy(List<OrderOption> orderOptions) {
        return orderOptions.stream().anyMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OrderOption orderOption) {
        return options.stream().anyMatch(option -> option.isSatisfiedBy(orderOption));
    }
}
