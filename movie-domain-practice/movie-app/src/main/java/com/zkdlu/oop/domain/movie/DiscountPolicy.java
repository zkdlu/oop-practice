package com.zkdlu.oop.domain.movie;

public abstract class DiscountPolicy {
    public int calculateDiscountFee(Showing showing) {
        return getDiscountFee(showing);
    }

    protected abstract int getDiscountFee(Showing showing);
}
