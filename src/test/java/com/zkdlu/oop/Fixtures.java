package com.zkdlu.oop;

import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.Order.OrderBuilder;
import com.zkdlu.oop.domain.order.OrderLineItem;
import com.zkdlu.oop.domain.order.OrderLineItem.OrderLineItemBuilder;
import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.Menu.MenuBuilder;
import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.Shop.ShopBuilder;

import java.util.List;

public class Fixtures {
    public static ShopBuilder aShop() {
        return Shop.builder()
                .name("고깃집")
                .commissionRate(0.1)
                .open(true)
                .minOrderAmount(13000)
                .commission(0);
    }

    public static MenuBuilder aMenu() {
        return Menu.builder()
                .name("고기")
                .description("맛있는 고기")
                .shop(aShop().build());
    }

    public static OrderLineItemBuilder anOrderLineItem() {
        return OrderLineItem.builder()
                .name("고기")
                .count(1)
                .menu(aMenu().build());
    }

    public static OrderBuilder anOrder() {
        return Order.builder()
                .shop(aShop().build())
                .orderLineItems(List.of(anOrderLineItem().build()));
    }
}
