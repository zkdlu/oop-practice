package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.zkdlu.oop.Fixtures.aMenu;
import static com.zkdlu.oop.Fixtures.aShop;
import static com.zkdlu.oop.Fixtures.anOrder;
import static com.zkdlu.oop.Fixtures.anOrderLineItem;
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
}