package com.zkdlu.oop.domain.order;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zkdlu.oop.Fixtures.anOrderLineItem;
import static com.zkdlu.oop.Fixtures.anOrderOption;
import static com.zkdlu.oop.Fixtures.anOrderOptionGroup;
import static org.assertj.core.api.Assertions.assertThat;

class OrderLineItemTest {
    @Test
    void 주문상품의_가격은_메뉴가격과_갯수를_곱한값이다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(List.of(
                        anOrderOptionGroup().orderOptions(List.of(
                                anOrderOption().price(1000).build()
                        )).build()
                ))
                .count(2)
                .build();

        assertThat(orderLineItem.calculateTotalPrice()).isEqualTo(2000);
    }
}