package com.zkdlu.oop.application.delivery;

import com.zkdlu.oop.domain.delivery.Delivery;
import com.zkdlu.oop.domain.delivery.DeliveryRepository;

public class SpyDeliveryRepository implements DeliveryRepository {
    public Delivery save_argumentDelivery;

    @Override
    public Delivery save(Delivery delivery) {
        save_argumentDelivery = delivery;
        return null;
    }
}
