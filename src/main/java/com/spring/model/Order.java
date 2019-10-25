package com.spring.model;

import com.spring.model.enums.Delivery;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "payed")
    private int payed;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery")
    private Delivery delivery;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_items", joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private List<Item> items;
}
