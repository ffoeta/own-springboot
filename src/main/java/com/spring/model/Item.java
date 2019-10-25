package com.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Data
public class Item extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "item_details",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "details_id", referencedColumnName = "id")})
    ItemDetails details;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "item_categories", joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "item_brands", joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")})
    Brand brand;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    List<Order> orders;
}
