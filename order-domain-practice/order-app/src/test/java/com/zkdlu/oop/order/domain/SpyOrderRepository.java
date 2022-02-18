package com.zkdlu.oop.order.domain;

import java.util.Optional;

public class SpyOrderRepository implements OrderRepository {
    public Order save_argumentOrder;
    public Optional<Order> findById_returnValue;

    @Override
    public Order save(Order order) {
        save_argumentOrder = order;
        return null;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return findById_returnValue;
    }
}
