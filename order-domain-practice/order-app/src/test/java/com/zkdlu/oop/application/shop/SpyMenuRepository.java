package com.zkdlu.oop.application.shop;

import com.zkdlu.oop.domain.shop.Menu;
import com.zkdlu.oop.domain.shop.MenuRepository;

import java.util.Optional;

public class SpyMenuRepository implements MenuRepository {
    public Optional<Menu> findById_returnValue;

    @Override
    public Optional<Menu> findById(Long menuId) {
        return findById_returnValue;
    }
}
