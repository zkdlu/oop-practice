package com.zkdlu.oop.domain.shop;

import java.util.ArrayList;
import java.util.List;

public class OptionGroup {
    private Long id;
    private String name;

    private boolean exclusive;
    private boolean basic;

    private List<Option> options = new ArrayList<>();
}
