package com.zkdlu.oop.application.shop;

import com.zkdlu.oop.domain.shop.Shop;
import com.zkdlu.oop.domain.shop.ShopRepository;

import java.util.Optional;

public class SpyShopRepository implements ShopRepository {
    public Optional<Shop> findById_returnValue;

    @Override
    public Optional<Shop> findById(Long shopId) {
        return findById_returnValue;
    }
}
