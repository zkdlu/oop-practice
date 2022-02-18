package com.zkdlu.oop.domain.shop;

import org.junit.jupiter.api.Test;

import static com.zkdlu.oop.Fixtures.aShop;
import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    void 수수료비율만큼_수수료를_계산한다() {
        Shop shop = aShop()
                .commission(0)
                .commissionRate(0.1)
                .build();

        var commission = shop.calculdateCommissionFee(10000);

        assertThat(commission).isEqualTo(1000);
    }
}