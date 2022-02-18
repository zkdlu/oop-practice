package com.zkdlu.oop.domain.billing;

import java.util.Optional;

public interface BillingRepository {
    Optional<Billing> findByShopId(Long shop);
}
