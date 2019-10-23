package com.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producers")
@Data
public class Producer extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    List<ItemDetails> itemDetails;
}