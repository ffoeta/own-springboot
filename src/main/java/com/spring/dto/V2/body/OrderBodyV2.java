package com.spring.dto.V2.body;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.dto.V2.def.ItemDtoV2;
import com.spring.model.Item;
import com.spring.model.enums.Delivery;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBodyV2 {
    private UUID id;
    private UUID user_id;
    private String name;
    private int payed;
    private String delivery;
    private List<UUID> items;
}
