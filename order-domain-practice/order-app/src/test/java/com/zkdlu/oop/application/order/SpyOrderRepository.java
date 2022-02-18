package com.zkdlu.oop.application.order;

import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderRepository;

import java.util.Optional;

public class SpyOrderRepository implements OrderRepository {
    public Order save_argumentOrder;

    @Override
    public Order save(Order order) {
        save_argumentOrder = order;
        return null;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.empty();
    }
}
