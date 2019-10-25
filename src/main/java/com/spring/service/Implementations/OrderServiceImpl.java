package com.spring.service.Implementations;

import com.spring.dto.V2.body.OrderBodyV2;
import com.spring.model.Item;
import com.spring.model.Order;
import com.spring.model.User;
import com.spring.model.enums.Delivery;
import com.spring.model.enums.Status;
import com.spring.repository.ItemRepository;
import com.spring.repository.OrderRepository;
import com.spring.repository.UserRepository;
import com.spring.service.interfaces.OrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order make(Order order) {
        Date date = new Date();
        order.setId(UUID.randomUUID());
        order.setCreated(date);
        order.setUpdated(date);
        order.setStatus(Status.ACTIVE);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByName(String name) {
        return orderRepository.findByName(name);
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getOrder(OrderBodyV2 orderBodyV2) {
        Order order = null;
        if (orderBodyV2.getId() != null) {
            order = findById(orderBodyV2.getId());
        } else if (orderBodyV2.getName() != null) {
            order = findByName(orderBodyV2.getName());
        }
        return order;
    }

    @Override
    public Order setOrder(Order order, OrderBodyV2 orderBodyV2) {
        if (order == null) {
            return null;
        }

        UUID id = orderBodyV2.getId();
        UUID user_id = orderBodyV2.getUser_id();
        String name = orderBodyV2.getName();
        List<UUID> items = orderBodyV2.getItems();
        String delivery = orderBodyV2.getDelivery();

        if (id != null) {
            order.setId(id);
        } else {
            order.setId(UUID.randomUUID());
        }
        if (user_id != null) {
            User user = userRepository.findById(user_id).orElse(null);
            if (user != null){
                order.setUser_id(user_id);
            }
        }
        if (name != null) {
            order.setName(name);
        }
        if (!items.isEmpty()) {
            List<Item> itemList = new ArrayList<>();
            items.forEach((item)->{
                Item itemTmp = itemRepository.findById(item).orElse(null);
                if (itemTmp != null) {
                    itemList.add(itemTmp);
                }
            });
            order.setItems(itemList);
        }
        if (delivery != null) {
            order.setDelivery(Delivery.valueOf(delivery));
        }
        return order;
    }
}
