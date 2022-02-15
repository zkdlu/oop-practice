package com.zkdlu.oop;

import antlr.build.ANTLR;
import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.Order.OrderBuilder;
import com.zkdlu.oop.domain.order.OrderLineItem;
import com.zkdlu.oop.domain.order.OrderLineItem.OrderLineItemBuilder;
import com.zkdlu.oop.domain.order.OrderOption;
import com.zkdlu.oop.domain.order.OrderOption.OrderOptionBuilder;
import com.zkdlu.oop.domain.order.OrderOptionGroup;
import com.zkdlu.oop.domain.order.OrderOptionGroup.OrderOptionGroupBuilder;
import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.Menu.MenuBuilder;
import com.zkdlu.oop.domain.shop.Option;
import com.zkdlu.oop.domain.shop.Option.OptionBuilder;
import com.zkdlu.oop.domain.shop.OptionGroup;
import com.zkdlu.oop.domain.shop.OptionGroup.OptionGroupBuilder;
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

    public static OptionBuilder anOption() {
        return Option.builder()
                .name("기본 구성")
                .price(10000);
    }

    public static OptionGroupBuilder anOptionGroup() {
        return OptionGroup.builder()
                .basic(true)
                .exclusive(true)
                .name("기본")
                .options(List.of(anOption().build()));
    }

    public static MenuBuilder aMenu() {
        return Menu.builder()
                .name("고기")
                .description("맛있는 고기")
                .shop(aShop().build())
                .optionGroups(List.of(anOptionGroup().build()));
    }

    public static OrderOptionBuilder anOrderOption() {
        return OrderOption.builder()
                .name("기본 구성")
                .price(10000);

    }

    public static OrderOptionGroupBuilder anOrderOptionGroup() {
        return OrderOptionGroup.builder()
                .name("기본")
                .orderOptions(List.of(anOrderOption().build()));
    }


    public static OrderLineItemBuilder anOrderLineItem() {
        return OrderLineItem.builder()
                .name("고기")
                .count(1)
                .menu(aMenu().build())
                .orderOptionGroups(List.of(
                        anOrderOptionGroup()
                                .name("기본")
                                .orderOptions(List.of(anOrderOption().name("기본 구성").price(10000).build()))
                                .build(),
                        anOrderOptionGroup()
                                .name("맛 선택")
                                .orderOptions(List.of(anOrderOption().name("매운 맛").price(1000). build()))
                                .build()));
    }

    public static OrderBuilder anOrder() {
        return Order.builder()
                .shop(aShop().build())
                .orderLineItems(List.of(anOrderLineItem().build()));
    }
}
