package com.zkdlu.oop.shop.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zkdlu.oop.Fixtures.aMenu;
import static com.zkdlu.oop.Fixtures.anOptionGroup;
import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @Test
    void 메뉴는_하나의_기본옵션그룹만_가질수있다() {
        var exception  = Assertions.assertThrows(IllegalStateException.class, () -> {
            aMenu().optionGroups(List.of(anOptionGroup().basic(true).build(), anOptionGroup().basic(true).build()))
                    .build();
        });

        assertThat(exception.getMessage()).isEqualTo("기본옵션그룹은 한개만 존재하여야 합니다.");
    }
}