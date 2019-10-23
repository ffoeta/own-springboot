package com.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "city_stores", joinColumns = {@JoinColumn(name = "city_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    private List<Store> stores;
}
