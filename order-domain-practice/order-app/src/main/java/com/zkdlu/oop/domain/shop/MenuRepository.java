package com.zkdlu.oop.domain.shop;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(Long menuId);

    List<Menu> findAllById(List<Long> collect);
}
