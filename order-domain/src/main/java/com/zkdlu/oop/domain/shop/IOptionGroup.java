package com.zkdlu.oop.domain.shop;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IOptionGroup {
    private String name;
    private List<IOption> orderOptions = new ArrayList<>();

    public IOptionGroup(String name, List<IOption> orderOptions) {
        this.name = name;
        this.orderOptions = orderOptions;
    }
}
