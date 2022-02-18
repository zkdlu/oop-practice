package com.zkdlu.oop.domain.shop;

import java.util.Optional;

public interface ShopRepository {
    Optional<Shop> findById(Long shopId);
}
