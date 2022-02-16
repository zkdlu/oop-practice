package com.zkdlu.oop.domain.shop;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean isSatisfiedBy(IOptionGroup orderOptionGroup) {
        if (!this.name.equals(orderOptionGroup.getName())) {
            return false;
        }

        List<IOption> satisfied = satisfied(orderOptionGroup.getOrderOptions());
        if (satisfied.isEmpty()) {
            return false;
        }

        if (exclusive && satisfied.size() > 1) {
            return false;
        }

        return true;
    }

    private List<IOption> satisfied(List<IOption> orderOptions) {
        return orderOptions.stream().filter(this::isSatisfiedBy).collect(Collectors.toList());
    }

    private boolean isSatisfiedBy(IOption orderOption) {
        return options.stream().anyMatch(option -> option.isSatisfiedBy(orderOption));
    }
}
