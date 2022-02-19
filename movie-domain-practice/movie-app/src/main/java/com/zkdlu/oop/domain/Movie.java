package com.zkdlu.oop.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Movie {
    private Long id;
    private String title;
    private int price;
    private DiscountPolicy discountPolicy;


    @Builder
    public Movie(Long id, String title, int price, DiscountPolicy discountPolicy) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    public int calculatePrice(Showing showing) {
        return price - discountPolicy.calculateDiscountFee(showing);
    }
}
