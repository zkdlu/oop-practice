package com.zkdlu.oop.shop.domain;

import java.util.List;
import java.util.Optional;

public class SpyMenuRepository implements MenuRepository {
    public Optional<Menu> findById_returnValue;
    public List<Menu> findAllById_returnValue;

    @Override
    public Optional<Menu> findById(Long menuId) {
        return findById_returnValue;
    }

    @Override
    public List<Menu> findAllById(List<Long> collect) {
        return findAllById_returnValue;
    }
}
