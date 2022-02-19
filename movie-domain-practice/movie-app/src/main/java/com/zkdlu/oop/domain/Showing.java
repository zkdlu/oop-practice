package com.zkdlu.oop.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class Showing {
    private Long id;
    private Movie movie;
    private LocalDateTime showingDate;
    private LocalTime runningTime;

    @Builder
    public Showing(Long id, Movie movie, LocalDateTime showingDate, LocalTime runningTime) {
        this.id = id;
        this.movie = movie;
        this.showingDate = showingDate;
        this.runningTime = runningTime;
    }

    public Reservation reserve(int count) {
        return new Reservation(
                showingDate,
                runningTime,
                count,
                movie.getPrice() * count,
                movie.calculatePrice(this) * count);
    }
}
