package com.zkdlu.oop.domain.movie;

public abstract class DiscountPolicy {
    public int calculateDiscountFee(Movie movie) {
        return getDiscountFee(movie);
    }

    protected abstract int getDiscountFee(Movie movie);
}
