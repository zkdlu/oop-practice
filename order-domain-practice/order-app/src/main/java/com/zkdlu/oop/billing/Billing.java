package com.zkdlu.oop.billing;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Billing {
    private Long id;
    private Long shopId;
    private int commission;

    @Builder
    public Billing(Long id, Long shopId, int commission) {
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }

    public Billing(Long shop) {
        this(null, shop, 0);
    }

    public void billCommissionFee(int commission) {
        this.commission += commission;
    }
}
