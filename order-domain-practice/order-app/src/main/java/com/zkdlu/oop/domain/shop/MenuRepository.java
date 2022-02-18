package com.zkdlu.oop.domain.shop;

import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(Long menuId);
}
