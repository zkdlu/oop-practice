package com.zkdlu.oop;

import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.Shop.ShopBuilder;

public class Fixtures {
    public static ShopBuilder aShop() {
        return Shop.builder()
                .name("고기")
                .commissionRate(0.1)
                .open(true)
                .minOrderAmount(13000)
                .commission(0);
    }
}
