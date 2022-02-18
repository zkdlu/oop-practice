package com.zkdlu.oop.domain.shop;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Shop {
    private Long id;
    private String name;
    private boolean open;
    private int minOrderAmount;
    private double commissionRate;
    private int commission;
    private List<Menu> menus = new ArrayList<>();

    @Builder
    public Shop(Long id, String name, boolean open, int minOrderAmount, double commissionRate, int commission) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
        this.commission = commission;
    }

    public boolean isValidOrderAmount(int totalPrice) {
        return minOrderAmount <= totalPrice;
    }

    public void billCommissionFee(int calculateTotalPrice) {
        commission += commissionRate * calculateTotalPrice;
    }
}
