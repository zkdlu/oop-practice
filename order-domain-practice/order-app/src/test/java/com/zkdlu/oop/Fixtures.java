package com.zkdlu.oop;

import com.zkdlu.oop.domain.delivery.Delivery;
import com.zkdlu.oop.domain.delivery.Delivery.DeliveryBuilder;
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
    /*
    가게
        고깃집
    메뉴
        고기
    기본 옵션그룹
        기본 옵션 10000원
    추가 옵션그룹
        추가 옵션1 1000원
        추가 옵션2 2000원
     */

    public static OptionBuilder anOption() {
        return Option.builder()
                .name("기본 옵션")
                .price(10000);
    }

    public static OptionGroupBuilder anOptionGroup() {
        return OptionGroup.builder()
                .basic(true)
                .exclusive(true)
                .name("기본 옵션그룹")
                .options(List.of(anOption().build()));
    }

    public static MenuBuilder aMenu() {
        return Menu.builder()
                .name("고기")
                .description("맛있는 고기")
                .shop(aShop().build())
                .optionGroups(List.of(
                        anOptionGroup().build(),
                        anOptionGroup().basic(false)
                                .name("추가 옵션그룹")
                                .options(List.of(
                                        anOption().name("추가 옵션1").price(1000).build(),
                                        anOption().name("추가 옵션2").price(2000).build()
                                ))
                                .build()));
    }

    public static ShopBuilder aShop() {
        return Shop.builder()
                .name("고깃집")
                .commissionRate(0.1)
                .open(true)
                .minOrderAmount(11000)
                .commission(0);
    }

    public static OrderOptionBuilder anOrderOption() {
        return OrderOption.builder()
                .name("기본 옵션")
                .price(10000);

    }

    public static OrderOptionGroupBuilder anOrderOptionGroup() {
        return OrderOptionGroup.builder()
                .name("기본 옵션그룹")
                .orderOptions(List.of(anOrderOption().build()));
    }


    public static OrderLineItemBuilder anOrderLineItem() {
        return OrderLineItem.builder()
                .name("고기")
                .count(1)
                .menu(aMenu().build().getId())
                .orderOptionGroups(List.of(
                        anOrderOptionGroup().build(),
                        anOrderOptionGroup()
                                .name("추가 옵션그룹")
                                .orderOptions(List.of(
                                        anOrderOption().name("추가 옵션1").price(1000).build(),
                                        anOrderOption().name("추가 옵션2").price(2000).build()))
                                .build()));
    }

    public static OrderBuilder anOrder() {
        return Order.builder()
                .shop(aShop().build().getId())
                .orderLineItems(List.of(anOrderLineItem().build()));
    }

    public static DeliveryBuilder aDelivery() {
        return Delivery.builder()
                .order(anOrder().build());
    }
}
