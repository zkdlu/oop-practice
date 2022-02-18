package com.zkdlu.oop.application.order;

import com.zkdlu.oop.application.delivery.SpyDeliveryRepository;
import com.zkdlu.oop.application.order.Cart.CartLineItem;
import com.zkdlu.oop.application.order.Cart.CartOption;
import com.zkdlu.oop.application.order.Cart.CartOptionGroup;
import com.zkdlu.oop.application.shop.SpyMenuRepository;
import com.zkdlu.oop.application.shop.SpyShopRepository;
import com.zkdlu.oop.domain.delivery.Delivery;
import com.zkdlu.oop.domain.delivery.Delivery.DeliveryState;
import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.zkdlu.oop.Fixtures.aDelivery;
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

    @BeforeEach
    void setUp() {
        spyOrderRepository = new SpyOrderRepository();
        spyShopRepository = new SpyShopRepository();
        spyMenuRepository = new SpyMenuRepository();
        spyDeliveryRepository = new SpyDeliveryRepository();

        OrderMapper orderMapper = new OrderMapper(spyShopRepository, spyMenuRepository);

        orderService = new OrderService(orderMapper, spyOrderRepository, spyDeliveryRepository);

        spyShopRepository.findById_returnValue = Optional.of(aShop().build());
        spyMenuRepository.findById_returnValue = Optional.of(aMenu().build());
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

        Delivery givenDelivery = aDelivery().build();
        spyDeliveryRepository.findById_returnValue = Optional.of(givenDelivery);

        orderService.completeOrder(1L);

        assertThat(givenOrder.getState()).isEqualTo(OrderState.COMPLETE);
        assertThat(givenDelivery.getState()).isEqualTo(DeliveryState.DELIVERED);
    }
}