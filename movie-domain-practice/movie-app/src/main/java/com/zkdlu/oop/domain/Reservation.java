package com.zkdlu.oop.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class Reservation {
    private Long id;
    private LocalDateTime showingDate;
    private LocalTime runningTime;
    private int count;
    private int totalAmount;
    private int paymentAmount;

    public Reservation(Long id, LocalDateTime showingDate, LocalTime runningTime, int count, int totalAmount, int paymentAmount) {
        this.id = id;
        this.showingDate = showingDate;
        this.runningTime = runningTime;
        this.count = count;
        this.totalAmount = totalAmount;
        this.paymentAmount = paymentAmount;
    }

    Reservation(LocalDateTime showingDate, LocalTime runningTime, int count, int totalAmount, int paymentAmount) {
        this(null, showingDate, runningTime, count, totalAmount, paymentAmount);
    }
}
