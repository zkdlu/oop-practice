package com.zkdlu.oop.domain.order;

import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.ShopRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private final ShopRepository shopRepository;

    public OrderValidator(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void validate(Order order) {
        validate(order, getShop(order.getShop()));
    }

    private Shop getShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
    }

    private void validate(Order order, Shop shop) {
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
            validateOrderLineItem(orderLineItem);
        }
    }

    private void validateOrderLineItem(OrderLineItem orderLineItem) {
        orderLineItem.validate();
    }
}
