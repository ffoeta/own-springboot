package com.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "details")
@Data
public class ItemDetails extends BaseEntity {

    @Column(name = "price")
    private String price;

    @Column(name = "camera_f")
    private String camera_f;

    @Column(name = "camera_b")
    private String camera_b;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "gpu")
    private String gpu;

    @Column(name = "nfc")
    private String nfc;

    @Column(name = "screen")
    private String screen;

    @Column(name = "diagonal")
    private String diagonal;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "details_producers", joinColumns = {@JoinColumn(name = "details_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id", referencedColumnName = "id")})
    Producer producer;

    @OneToOne(mappedBy = "details", fetch = FetchType.LAZY)
    Item item;



}
