package com.zkdlu.oop.domain.delivery;

import java.util.Optional;

public interface DeliveryRepository {
    Delivery save(Delivery delivery);

    Optional<Delivery> findById(long deliveryId);
}
