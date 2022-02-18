package com.zkdlu.oop.shop.domain;

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

        validate();
    }

    private void validate() {
        if (optionGroups.stream().filter(OptionGroup::isBasic).count() > 1) {
            throw new IllegalStateException("기본옵션그룹은 한개만 존재하여야 합니다.");
        }
    }
}
