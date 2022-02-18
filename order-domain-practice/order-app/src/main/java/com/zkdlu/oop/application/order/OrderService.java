package com.zkdlu.oop.application.order;

import com.zkdlu.oop.domain.delivery.Delivery;
import com.zkdlu.oop.domain.delivery.DeliveryRepository;
import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, DeliveryRepository deliveryRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public void placeOrder(Cart cart) {
        Order order = orderMapper.mapFrom(cart);
        order.place();
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
