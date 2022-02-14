package com.zkdlu.oop.domain.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Shop {
    private Long id;
    private String name;
    private boolean open;
    private int minOrderAmount;
    private double commissionRate;
    private int commission;

    @Builder
    public Shop(Long id, String name, boolean open, int minOrderAmount, double commissionRate, int commission) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
        this.commission = commission;
    }

    public boolean isValidOrderAmount(int orderAmount) {
        return minOrderAmount <= orderAmount;
    }
}
