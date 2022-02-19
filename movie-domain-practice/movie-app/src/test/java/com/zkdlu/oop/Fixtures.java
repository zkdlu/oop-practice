package com.zkdlu.oop;

import com.zkdlu.oop.domain.DiscountPolicy;
import com.zkdlu.oop.domain.DiscountPolicy.DiscountPolicyBuilder;
import com.zkdlu.oop.domain.Movie;
import com.zkdlu.oop.domain.Movie.MovieBuilder;
import com.zkdlu.oop.domain.Showing;
import com.zkdlu.oop.domain.Showing.ShowingBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Fixtures {

    public static DiscountPolicyBuilder aDiscountPolicy() {
        return DiscountPolicy.builder()
                .discountAmount(1000);
    }

    public static MovieBuilder aMovie() {
        return Movie.builder()
                .title("영화")
                .price(8000)
                .discountPolicy(aDiscountPolicy().build());
    }

    public static ShowingBuilder aShowing() {
        return Showing.builder()
                .showingDate(LocalDateTime.of(2022,2,19,12,16))
                .runningTime(LocalTime.of(2, 30))
                .movie(aMovie().build());
    }
}
