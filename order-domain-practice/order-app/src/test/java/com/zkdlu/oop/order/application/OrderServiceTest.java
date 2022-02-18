package com.zkdlu.oop.order.application;

import com.zkdlu.oop.delivery.Delivery.DeliveryState;
import com.zkdlu.oop.delivery.domain.SpyDeliveryRepository;
import com.zkdlu.oop.order.application.Cart.CartLineItem;
import com.zkdlu.oop.order.application.Cart.CartOption;
import com.zkdlu.oop.order.application.Cart.CartOptionGroup;
import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderState;
import com.zkdlu.oop.order.domain.OrderValidator;
import com.zkdlu.oop.order.domain.SpyOrderRepository;
import com.zkdlu.oop.shop.domain.SpyMenuRepository;
import com.zkdlu.oop.shop.domain.SpyShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.zkdlu.oop.Fixtures.aMenu;
import static com.zkdlu.oop.Fixtures.aShop;
import static com.zkdlu.oop.Fixtures.anOrder;
import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private OrderService orderService;

    private SpyOrderRepository spyOrderRepository;
    private SpyShopRepository spyShopRepository;
    private SpyMenuRepository spyMenuRepository;
    private SpyDeliveryRepository spyDeliveryRepository;
    private OrderValidator orderValidator;

    @BeforeEach
    void setUp() {
        spyOrderRepository = new SpyOrderRepository();
        spyShopRepository = new SpyShopRepository();
        spyMenuRepository = new SpyMenuRepository();
        spyDeliveryRepository = new SpyDeliveryRepository();
        orderValidator = new OrderValidator(spyShopRepository, spyMenuRepository);

        OrderMapper orderMapper = new OrderMapper(spyShopRepository, spyMenuRepository);

        orderService = new OrderService(orderMapper, spyOrderRepository, orderValidator);

        spyShopRepository.findById_returnValue = Optional.of(aShop().build());
        spyMenuRepository.findById_returnValue = Optional.of(aMenu().build());
        spyMenuRepository.findAllById_returnValue = List.of(aMenu().build());
    }

    @Test
    void 주문시_주문완료상태가_된다() {
        Cart cart = new Cart(1L, 1L,
                new CartLineItem(1L, "고기", 2,
                        new CartOptionGroup("기본 옵션그룹",
                                new CartOption("기본 옵션", 10000))));

        orderService.placeOrder(cart);

        assertThat(spyOrderRepository.save_argumentOrder.getState()).isEqualTo(OrderState.ORDERED);
    }

    @Test
    void 결제시_결제완료상태가_된다() {
        Order givenOrder = anOrder().build();
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.payOrder(1L);

        assertThat(givenOrder.getState()).isEqualTo(OrderState.PAYED);
    }

    @Test
    void 결제시_배송을_시작한다() {
        Order givenOrder = anOrder().build();
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.payOrder(1L);

        assertThat(spyDeliveryRepository.save_argumentDelivery.getState()).isEqualTo(DeliveryState.DELIVERING);
    }

    @Test
    void 주문이_완료되면_배송완료_처리된다() {
        Order givenOrder = anOrder().build();
        spyOrderRepository.findById_returnValue = Optional.of(givenOrder);

        orderService.completeOrder(1L);

        assertThat(givenOrder.getState()).isEqualTo(OrderState.COMPLETE);
    }
}