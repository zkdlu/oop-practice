package com.zkdlu.oop.order.application;

import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderRepository;
import com.zkdlu.oop.order.domain.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, OrderValidator orderValidator) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    @Transactional
    public void placeOrder(Cart cart) {
        Order order = orderMapper.mapFrom(cart);
        order.place(orderValidator);
        orderRepository.save(order);
    }

    @Transactional
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.payed();
    }

    @Transactional
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.complete();
    }
}
