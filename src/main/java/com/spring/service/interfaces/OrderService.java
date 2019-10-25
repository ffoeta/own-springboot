package com.spring.service.interfaces;

import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.dto.V2.body.OrderBodyV2;
import com.spring.model.Item;
import com.spring.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order make(Order order);

    Order update(Order order);

    Order findByName(String name);

    Order findById(UUID id);

    void deleteById(UUID id);

    Order getOrder(OrderBodyV2 orderBodyV2);

    Order setOrder(Order order, OrderBodyV2 orderBodyV2);
}
