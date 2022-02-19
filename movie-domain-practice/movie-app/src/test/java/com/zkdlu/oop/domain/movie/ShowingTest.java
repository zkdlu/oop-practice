package com.zkdlu.oop.domain.movie;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.zkdlu.oop.Fixtures.aMovie;
import static com.zkdlu.oop.Fixtures.aShowing;
import static org.assertj.core.api.Assertions.assertThat;

class ShowingTest {

    @Test
    void 예약시_예약정보대로_예매된다() {
        Showing showing = aShowing()
                .showingDate(LocalDateTime.of(2021, 2, 19, 12, 22, 0))
                .runningTime(LocalTime.of(2, 30))
                .movie(aMovie().price(10000).build())
                .build();

        Reservation reservation = showing.reserve(2);

        assertThat(reservation.getCount()).isEqualTo(2);
        assertThat(reservation.getShowingDate()).isEqualTo(LocalDateTime.of(2021, 2, 19, 12, 22, 0));
        assertThat(reservation.getRunningTime()).isEqualTo(LocalTime.of(2, 30));
        assertThat(reservation.getRunningTime()).isEqualTo(LocalTime.of(2, 30));
        assertThat(reservation.getTotalAmount()).isEqualTo(20000);
    }

    @Test
    void 예약시_고정할인이_적용된다() {
        Showing showing = aShowing()
                .movie(aMovie()
                        .price(10000)
                        .discountPolicy(new AmountDiscountPolicy(800))
                        .build())
                .build();

        Reservation reservation = showing.reserve(2);

        assertThat(reservation.getPaymentAmount()).isEqualTo(18400);
    }

    @Test
    void 예약시_비율할인이_적용된다() {
        Showing showing = aShowing()
                .movie(aMovie()
                        .price(10000)
                        .discountPolicy(new RateDiscountPolicy(0.1))
                        .build())
                .build();

        Reservation reservation = showing.reserve(2);

        assertThat(reservation.getPaymentAmount()).isEqualTo(18000);
    }
}