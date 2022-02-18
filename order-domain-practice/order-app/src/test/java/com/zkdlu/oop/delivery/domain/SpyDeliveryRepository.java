package com.zkdlu.oop.delivery.domain;

import com.zkdlu.oop.delivery.Delivery;
import com.zkdlu.oop.delivery.DeliveryRepository;

import java.util.Optional;

public class SpyDeliveryRepository implements DeliveryRepository {
    public Delivery save_argumentDelivery;
    public Optional<Delivery> findById_returnValue;

    @Override
    public Delivery save(Delivery delivery) {
        save_argumentDelivery = delivery;
        return null;
    }

    @Override
    public Optional<Delivery> findById(long deliveryId) {
        return findById_returnValue;
    }
}
