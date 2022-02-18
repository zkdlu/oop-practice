package com.zkdlu.oop.billing;

import java.util.Optional;

public interface BillingRepository {
    Optional<Billing> findByShopId(Long shop);
}
