package com.zkdlu.oop.domain.shop;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Long id;
    private String name;
    private String description;
    private Shop shop;

    private List<OptionGroup> optionGroups = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, String description, Shop shop) {
        this.id = id;
        this.name = name;
        this.description = description;

        shop.getMenus().add(this);
    }
}
