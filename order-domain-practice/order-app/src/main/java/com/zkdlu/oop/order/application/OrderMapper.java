package com.zkdlu.oop.order.application;

import com.zkdlu.oop.order.application.Cart.CartLineItem;
import com.zkdlu.oop.order.application.Cart.CartOptionGroup;
import com.zkdlu.oop.order.domain.Order;
import com.zkdlu.oop.order.domain.OrderLineItem;
import com.zkdlu.oop.order.domain.OrderOption;
import com.zkdlu.oop.order.domain.OrderOptionGroup;
import com.zkdlu.oop.shop.domain.Menu;
import com.zkdlu.oop.shop.domain.MenuRepository;
import com.zkdlu.oop.shop.domain.Shop;
import com.zkdlu.oop.shop.domain.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderMapper {
    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

    public Order mapFrom(Cart cart) {
        Shop shop = shopRepository.findById(cart.getShopId())
                .orElseThrow(IllegalArgumentException::new);

        return new Order(
                shop.getId(),
                cart.getCartLineItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(Collectors.toList())
        );
    }

    private OrderLineItem toOrderLineItem(CartLineItem cartLineItem) {
        Menu menu = menuRepository.findById(cartLineItem.getMenuId())
                .orElseThrow(IllegalArgumentException::new);

        return new OrderLineItem(
                cartLineItem.getName(),
                cartLineItem.getCount(),
                menu.getId(),
                cartLineItem.getGroups()
                        .stream()
                        .map(this::toOrderOptionGroup)
                        .collect(Collectors.toList()));
    }

    private OrderOptionGroup toOrderOptionGroup(CartOptionGroup cartOptionGroup) {
        return new OrderOptionGroup(
                cartOptionGroup.getName(),
                cartOptionGroup.getOptions()
                        .stream()
                        .map(this::toOrderOption)
                        .collect(Collectors.toList()));
    }

    private OrderOption toOrderOption(Cart.CartOption cartOption) {
        return new OrderOption(
                cartOption.getName(),
                cartOption.getPrice()
        );
    }
}
