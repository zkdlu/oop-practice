package com.zkdlu.oop.domain.shop;

import com.zkdlu.oop.domain.order.OrderOptionGroup;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Menu {
    private Long id;
    private String name;
    private String description;
    private Shop shop;

    private List<OptionGroup> optionGroups = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, String description, Shop shop, List<OptionGroup> optionGroups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shop = shop;

        this.shop.getMenus().add(this);
        this.optionGroups.addAll(optionGroups);
    }

    public void validateOrderItem(String name, List<OrderOptionGroup> orderOptionGroups) {
        if (!this.name.equals(name)) {
            throw new IllegalStateException("주문중에 메뉴정보가 변경되었습니다.");
        }

        validateOptionGroup(orderOptionGroups);
    }

    private void validateOptionGroup(List<OrderOptionGroup> orderOptionGroups) {
        optionGroups.stream().forEach(optionGroup ->
                optionGroup.validateOptionGroup(orderOptionGroups));
    }
}
