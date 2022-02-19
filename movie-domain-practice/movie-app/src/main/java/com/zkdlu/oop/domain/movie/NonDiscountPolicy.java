package com.zkdlu.oop.domain.movie;

public class NonDiscountPolicy extends DiscountPolicy{
    @Override
    protected int getDiscountFee(Movie movie) {
        return 0;
    }
}
