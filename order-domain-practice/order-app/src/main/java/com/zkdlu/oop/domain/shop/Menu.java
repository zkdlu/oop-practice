package com.zkdlu.oop.domain.shop;

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

    public void validateOrderItem(String name, List<IOptionGroup> orderOptionGroups) {
        if (!this.name.equals(name)) {
            throw new IllegalStateException("기본상품이 변경되었습니다.");
        }

        if (!isSatisfiedBy(orderOptionGroups)) {
            throw new IllegalStateException("메뉴가 변경됐습니다.");
        }
    }

    private boolean isSatisfiedBy(List<IOptionGroup> orderOptionGroups) {
        return orderOptionGroups.stream().anyMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(IOptionGroup orderOptionGroup) {
        return optionGroups.stream().anyMatch(optionGroup -> optionGroup.isSatisfiedBy(orderOptionGroup));
    }
}
