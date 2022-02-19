package com.zkdlu.oop.domain.movie;

public class NonDiscountPolicy extends DiscountPolicy{
    @Override
    protected int getDiscountFee(Showing showing) {
        return 0;
    }
}
