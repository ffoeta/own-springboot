package com.spring.dto.V2.anon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.City;
import com.spring.model.ItemDetails;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonItemDetailsDtoV2 {

    @JsonIgnore
    private UUID id;

    private int price;

    private String camera_f;

    private String camera_b;

    private String cpu;

    private String gpu;

    private String nfc;

    private String screen;

    private String diagonal;

    @JsonIgnore
    private String notes;

    @JsonIgnore
    private Status status;


    public static AnonItemDetailsDtoV2 from(ItemDetails itemDetails) {
        AnonItemDetailsDtoV2 anonItemDetailsDtoV2 = new AnonItemDetailsDtoV2();

        anonItemDetailsDtoV2.setId(itemDetails.getId());
        anonItemDetailsDtoV2.setPrice(itemDetails.getPrice());
        anonItemDetailsDtoV2.setCamera_f(itemDetails.getCamera_f());
        anonItemDetailsDtoV2.setCamera_b(itemDetails.getCamera_b());
        anonItemDetailsDtoV2.setCpu(itemDetails.getCpu());
        anonItemDetailsDtoV2.setGpu(itemDetails.getGpu());
        anonItemDetailsDtoV2.setNfc(itemDetails.getNfc());
        anonItemDetailsDtoV2.setDiagonal(itemDetails.getDiagonal());
        anonItemDetailsDtoV2.setNotes(itemDetails.getNotes());
        anonItemDetailsDtoV2.setStatus(itemDetails.getStatus());


        return anonItemDetailsDtoV2;
    }

    public ItemDetails to() {
        ItemDetails itemDetails = new ItemDetails();

        itemDetails.setId(id);
        itemDetails.setPrice(price);
        itemDetails.setCamera_f(camera_f);
        itemDetails.setCamera_b(camera_b);
        itemDetails.setCpu(cpu);
        itemDetails.setGpu(gpu);
        itemDetails.setNfc(nfc);
        itemDetails.setDiagonal(diagonal);
        itemDetails.setNotes(notes);
        itemDetails.setStatus(status);

        return itemDetails;
    }
}
