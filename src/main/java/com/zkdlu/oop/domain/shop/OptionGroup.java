package com.zkdlu.oop.domain.shop;

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
}
