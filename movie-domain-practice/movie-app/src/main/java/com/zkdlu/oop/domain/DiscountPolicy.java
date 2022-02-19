package com.zkdlu.oop.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DiscountPolicy {
    private int discountAmount;

    @Builder
    public DiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int calculateDiscountFee(Showing showing) {
        return discountAmount;
    }
}
