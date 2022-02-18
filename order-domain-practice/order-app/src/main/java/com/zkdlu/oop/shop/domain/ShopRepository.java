package com.zkdlu.oop.shop.domain;

import java.util.Optional;

public interface ShopRepository {
    Optional<Shop> findById(Long shopId);
}
