package com.zkdlu.oop.shop.domain;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(Long menuId);

    List<Menu> findAllById(List<Long> collect);
}
