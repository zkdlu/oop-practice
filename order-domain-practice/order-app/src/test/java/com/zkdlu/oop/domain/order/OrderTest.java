package com.zkdlu.oop.domain.order;

import org.junit.jupiter.api.Test;

import static com.zkdlu.oop.Fixtures.anOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class OrderTest {

    @Test
    void 주문성공시_주문상태를_주문완료상태로_변경한다() {
        Order order = anOrder().build();

        order.place(mock(OrderValidator.class));

        assertThat(order.getState()).isEqualTo(OrderState.ORDERED);
    }
}