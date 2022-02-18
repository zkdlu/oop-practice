package com.zkdlu.oop.application.order;

import com.zkdlu.oop.application.order.Cart.CartLineItem;
import com.zkdlu.oop.application.order.Cart.CartOptionGroup;
import com.zkdlu.oop.domain.order.Order;
import com.zkdlu.oop.domain.order.OrderLineItem;
import com.zkdlu.oop.domain.order.OrderOption;
import com.zkdlu.oop.domain.order.OrderOptionGroup;
import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.MenuRepository;
import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.ShopRepository;
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
                menu,
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
