package com.zkdlu.oop.application.order;

import com.zkdlu.oop.domain.delivery.Delivery;
import com.zkdlu.oop.domain.delivery.DeliveryRepository;
import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderRepository;
import com.zkdlu.oop.domain.order.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, DeliveryRepository deliveryRepository, OrderValidator orderValidator) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
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

        Delivery delivery = Delivery.started(order);
        deliveryRepository.save(delivery);
    }

    @Transactional
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        order.complete();

        Delivery delivery = deliveryRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);;
        delivery.complete();
    }
}
