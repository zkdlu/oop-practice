package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.MenuRepository;
import com.zkdlu.oop.domain.shop.OptionGroup;
import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.ShopRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
public class OrderValidator {
    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;

    public OrderValidator(ShopRepository shopRepository, MenuRepository menuRepository) {
        this.shopRepository = shopRepository;
        this.menuRepository = menuRepository;
    }

    public void validate(Order order) {
        validate(order, getShop(order.getShop()), getMenus(order));
    }

    private Map<Long, Menu> getMenus(Order order) {
        return menuRepository.findAllById(order.getOrderLineItems().stream().map(OrderLineItem::getMenu).collect(toList()))
                .stream()
                .collect(toMap(Menu::getId, identity()));
    }

    private Shop getShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
    }

    private void validate(Order order, Shop shop, Map<Long, Menu> menus) {
        if (order.getOrderLineItems().isEmpty()) {
            throw new IllegalStateException("주문항목이 비어있습니다.");
        }

        if (!shop.isOpen()) {
            throw new IllegalStateException("가게가 영업중이 아닙니다.");
        }

        if (!shop.isValidOrderAmount(order.calculateTotalPrice())) {
            throw new IllegalStateException(
                    String.format("최소 주문 금액 %s원 이상을 넘겨주세요.", shop.getMinOrderAmount()));
        }

        for (var orderLineItem : order.getOrderLineItems()) {
            validateOrderLineItem(orderLineItem, menus.get(orderLineItem.getMenu()));
        }
    }

    private void validateOrderLineItem(OrderLineItem orderLineItem, Menu menu) {
        if (!menu.getName().equals(orderLineItem.getName())) {
            throw new IllegalStateException("기본상품이 변경되었습니다.");
        }

        for (OrderOptionGroup group : orderLineItem.getOrderOptionGroups()) {
            validateOrderOptionGroup(group, menu);
        }
    }

    private void validateOrderOptionGroup(OrderOptionGroup group, Menu menu) {
        for (OptionGroup optionGroup : menu.getOptionGroups()) {
            if (optionGroup.isSatisfiedBy(group.convertToOptionGroup())) {
                return;
            }
        }

        throw new IllegalStateException("메뉴가 변경됐습니다.");
    }
}
