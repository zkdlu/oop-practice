package com.zkdlu.oop.domain.shop;

import com.zkdlu.oop.domain.order.OrderOption;
import com.zkdlu.oop.domain.order.OrderOptionGroup;
import lombok.Builder;

import java.util.List;

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

    public void validateOption(OrderOptionGroup orderOptionGroup) {
        if (!isValidOption(orderOptionGroup.getOrderOptions())) {
            throw new IllegalStateException("주문중에 옵션정보가 변경되었습니다.");
        }
    }

    private boolean isValidOption(List<OrderOption> orderOptions) {
        return orderOptions.stream().anyMatch(this::isMatched);
    }

    private boolean isMatched(OrderOption orderOption) {
        return this.name.equals(orderOption.getName()) && this.price == orderOption.getPrice();
    }
}
