package com.zkdlu.oop.shop.domain;

import java.util.Optional;

public class SpyShopRepository implements ShopRepository {
    public Optional<Shop> findById_returnValue;

    @Override
    public Optional<Shop> findById(Long shopId) {
        return findById_returnValue;
    }
}
