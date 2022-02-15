package com.zkdlu.oop.domain.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.zkdlu.oop.Fixtures.aMenu;
import static com.zkdlu.oop.Fixtures.aShop;
import static com.zkdlu.oop.Fixtures.anOrder;
import static com.zkdlu.oop.Fixtures.anOrderLineItem;
import static com.zkdlu.oop.Fixtures.anOrderOption;
import static com.zkdlu.oop.Fixtures.anOrderOptionGroup;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void 주문시_가게는_영업중이어야_한다() {
        Order order = anOrder()
                .shop(aShop().open(false).build())
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place();
        });

        assertThat(exception.getMessage()).isEqualTo("가게가 영업중이 아닙니다.");
    }

    @Test
    void 주문시_주문항목은_비어있으면_안된다() {
        Order order = anOrder()
                .orderLineItems(Collections.emptyList())
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place();
        });

        assertThat(exception.getMessage()).isEqualTo("주문항목이 비어있습니다.");
    }

    @Test
    void 주문시_가게_최소주문금액을_만족해야한다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .orderOptions(
                                        List.of(anOrderOption().price(5000).build())
                                ).build()
                        )
                )
                .build();

        Order order = anOrder()
                .shop(aShop().minOrderAmount(10000).build())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place();
        });

        assertThat(exception.getMessage()).isEqualTo("최소 주문 금액 10000원 이상을 넘겨주세요.");
    }

    @Test
    void 주문시_메뉴의_이름이_변경되면_안된다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .menu(aMenu().name("기본 메뉴").build())
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .orderOptions(
                                        List.of(anOrderOption().price(13000).build())
                                ).build()
                        )
                ).build();

        Order order = anOrder()
                .shop(aShop().minOrderAmount(10000).build())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place();
        });

        assertThat(exception.getMessage()).isEqualTo("주문중에 메뉴정보가 변경되었습니다.");
    }

    @Test
    void 주문시_옵션그룹의_이름이_변경되면_안된다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .name("기본 옵션 그룹")
                                .orderOptions(
                                        List.of(anOrderOption().price(13000).build())
                                ).build()
                        )
                ).build();

        Order order = anOrder()
                .shop(aShop().minOrderAmount(10000).build())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place();
        });

        assertThat(exception.getMessage()).isEqualTo("주문중에 옵션그룹정보가 변경되었습니다.");
    }
}