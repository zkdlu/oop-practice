package com.zkdlu.oop.domain.shop;

import org.junit.jupiter.api.Test;

import static com.zkdlu.oop.Fixtures.aShop;
import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    void 수수료비율만큼_수수료를_획득한다() {
        Shop shop = aShop()
                .commission(0)
                .commissionRate(0.1)
                .build();

        shop.billCommissionFee(10000);

        assertThat(shop.getCommission()).isEqualTo(1000);
    }
}