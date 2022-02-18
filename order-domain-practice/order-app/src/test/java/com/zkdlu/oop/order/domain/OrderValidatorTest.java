package com.zkdlu.oop.order.domain;

import com.zkdlu.oop.shop.domain.Menu;
import com.zkdlu.oop.shop.domain.OptionGroup;
import com.zkdlu.oop.shop.domain.Shop;
import com.zkdlu.oop.shop.domain.SpyMenuRepository;
import com.zkdlu.oop.shop.domain.SpyShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.zkdlu.oop.Fixtures.aMenu;
import static com.zkdlu.oop.Fixtures.aShop;
import static com.zkdlu.oop.Fixtures.anOption;
import static com.zkdlu.oop.Fixtures.anOptionGroup;
import static com.zkdlu.oop.Fixtures.anOrder;
import static com.zkdlu.oop.Fixtures.anOrderLineItem;
import static com.zkdlu.oop.Fixtures.anOrderOption;
import static com.zkdlu.oop.Fixtures.anOrderOptionGroup;
import static org.assertj.core.api.Assertions.assertThat;

class OrderValidatorTest {

    private OrderValidator orderValidator;
    private SpyShopRepository spyShopRepository;
    private SpyMenuRepository spyMenuRepository;

    @BeforeEach
    void setUp() {
        spyShopRepository = new SpyShopRepository();
        spyShopRepository.findById_returnValue = Optional.of(aShop().build());

        spyMenuRepository = new SpyMenuRepository();
        spyMenuRepository.findAllById_returnValue = List.of(aMenu().build());

        orderValidator = new OrderValidator(spyShopRepository, spyMenuRepository);
    }

    @Test
    void 주문시_가게는_영업중이어야_한다() {
        Shop shop = aShop().open(false).build();
        spyShopRepository.findById_returnValue = Optional.of(shop);

        Order order = anOrder()
                .shop(shop.getId())
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("가게가 영업중이 아닙니다.");
    }

    @Test
    void 주문시_주문항목은_비어있으면_안된다() {
        Order order = anOrder()
                .orderLineItems(Collections.emptyList())
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("주문항목이 비어있습니다.");
    }

    @Test
    void 주문시_가게_최소주문금액을_만족해야한다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .orderOptions(
                                        List.of(anOrderOption().price(5000).build())
                                ).build()
                        )
                )
                .build();
        Shop shop = aShop().minOrderAmount(10000).build();
        spyShopRepository.findById_returnValue = Optional.of(shop);
        Order order = anOrder()
                .shop(shop.getId())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("최소 주문 금액 10000원 이상을 넘겨주세요.");
    }

    @Test
    void 주문시_메뉴의_이름이_변경되면_안된다() {
        Menu menu = aMenu().name("기본 메뉴").build();
        spyMenuRepository.findAllById_returnValue = List.of(menu);
        OrderLineItem orderLineItem = anOrderLineItem()
                .menu(menu.getId())
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .orderOptions(
                                        List.of(anOrderOption().build())
                                ).build()
                        )
                ).build();

        Shop shop = aShop().minOrderAmount(10000).build();
        spyShopRepository.findById_returnValue = Optional.of(shop);
        Order order = anOrder()
                .shop(shop.getId())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("기본상품이 변경되었습니다.");
    }

    @Test
    void 주문시_옵션그룹의_이름이_변경되면_안된다() {
        spyShopRepository.findById_returnValue = Optional.of(aShop().minOrderAmount(10000).build());

        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .name("기본 옵션 그룹")
                                .orderOptions(
                                        List.of(anOrderOption().build())
                                ).build()
                        )
                ).build();

        Shop shop = aShop().minOrderAmount(10000).build();
        spyShopRepository.findById_returnValue = Optional.of(shop);
        Order order = anOrder()
                .shop(shop.getId())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("메뉴가 변경됐습니다.");
    }

    @Test
    void 주문시_옵션의_정보가_변경되면_안된다() {
        OrderLineItem orderLineItem = anOrderLineItem()
                .orderOptionGroups(
                        List.of(anOrderOptionGroup()
                                .orderOptions(
                                        List.of(anOrderOption().name("변경된 옵션").build())
                                ).build()
                        )
                ).build();

        Shop shop = aShop().minOrderAmount(10000).build();
        spyShopRepository.findById_returnValue = Optional.of(shop);
        Order order = anOrder()
                .shop(shop.getId())
                .orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("메뉴가 변경됐습니다.");
    }

    @Test
    void 주문시_단일옵션은_하나만_선택되어야_한다() {
        OptionGroup optionGroup = anOptionGroup()
                .exclusive(true)
                .options(List.of(
                        anOption().name("옵션1").build(),
                        anOption().name("옵션2").build()))
                .build();
        Menu menu = aMenu().optionGroups(List.of(optionGroup)).build();
        spyMenuRepository.findAllById_returnValue = List.of(menu);

        OrderLineItem orderLineItem = anOrderLineItem()
                .menu(menu.getId())
                .orderOptionGroups(List.of(
                        anOrderOptionGroup()
                                .orderOptions(List.of(
                                        anOrderOption().name("옵션1").build(),
                                        anOrderOption().name("옵션2").build()
                                )).build()))
                .build();

        Order order = anOrder().orderLineItems(List.of(orderLineItem))
                .build();

        var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            order.place(orderValidator);
        });

        assertThat(exception.getMessage()).isEqualTo("메뉴가 변경됐습니다.");
    }
}