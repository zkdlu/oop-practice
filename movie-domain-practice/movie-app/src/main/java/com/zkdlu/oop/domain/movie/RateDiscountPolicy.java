package com.zkdlu.oop.domain.movie;

public class RateDiscountPolicy extends DiscountPolicy {
    private double discountRate;

    public RateDiscountPolicy(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    protected int getDiscountFee(Showing showing) {
        return (int)(showing.getFee() * discountRate);
    }
}
