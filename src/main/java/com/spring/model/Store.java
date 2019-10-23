package com.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Store extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "stores", fetch = FetchType.LAZY)
    List<City> cities;

}