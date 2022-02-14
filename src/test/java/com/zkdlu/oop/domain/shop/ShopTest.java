package com.zkdlu.oop.domain.shop;

import org.junit.jupiter.api.Test;

import static com.zkdlu.oop.Fixtures.aShop;
import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    void 최소주문금액_체크() {
        Shop shop = aShop().minOrderAmount(15000).build();

        assertThat(shop.isValidOrderAmount(10000)).isFalse();
        assertThat(shop.isValidOrderAmount(15000)).isTrue();
        assertThat(shop.isValidOrderAmount(16000)).isTrue();
    }
}