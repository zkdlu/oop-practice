package com.zkdlu.oop.domain.movie;

public class AmountDiscountPolicy extends DiscountPolicy {
    private int discountAmount;

    public AmountDiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    protected int getDiscountFee(Movie movie) {
        return discountAmount;
    }
}
