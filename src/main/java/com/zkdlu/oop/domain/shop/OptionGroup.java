package com.zkdlu.oop.domain.shop;

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

    public void validateOption(List<OrderOptionGroup> orderOptionGroups) {
        if (!isValidOptionGroup(orderOptionGroups)) {
            throw new IllegalStateException("주문중에 옵션그룹정보가 변경되었습니다.");
        }
    }

    private boolean isValidOptionGroup(List<OrderOptionGroup> orderOptionGroups) {
        return orderOptionGroups.stream().anyMatch(orderOptionGroup -> this.name.equals(orderOptionGroup.getName()));
    }
}
