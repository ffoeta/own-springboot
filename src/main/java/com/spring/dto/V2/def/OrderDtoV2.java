package com.spring.dto.V2.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.model.Item;
import com.spring.model.Order;
import com.spring.model.enums.Delivery;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDtoV2 {

    private String name;
    private int payed;
    private String delivery;
    private List<ItemDtoV2> items;

    public Order to(){

        Order order = new Order();

        List<Item> list = new ArrayList<>();

        order.setName(name);
        order.setDelivery(Delivery.valueOf(delivery));

        items.forEach(item -> {
            list.add(item.to());
            payed += item.getDetails().getPrice();
        });

        order.setItems(list);
        order.setPayed(payed);

        return order;
    }

    public static OrderDtoV2 from(Order order){
        OrderDtoV2 orderDtoV2 = new OrderDtoV2();

        orderDtoV2.setName(order.getName());
        orderDtoV2.setDelivery(order.getDelivery().toString());

        List<ItemDtoV2> list = new ArrayList<>();
        int payed = 0;

        order.getItems().forEach(item -> {
            list.add(ItemDtoV2.from(item));
            orderDtoV2.setPayed(orderDtoV2.getPayed() + item.getDetails().getPrice());
        });

        orderDtoV2.setItems(list);

        return orderDtoV2;
    }
}
