package com.spring.dto.V2.def;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.ItemDetails;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetailsDtoV2 {

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


    public static ItemDetailsDtoV2 from(ItemDetails itemDetails) {
        ItemDetailsDtoV2 itemDetailsDtoV2 = new ItemDetailsDtoV2();

        itemDetailsDtoV2.setId(itemDetails.getId());
        itemDetailsDtoV2.setPrice(itemDetails.getPrice());
        itemDetailsDtoV2.setCamera_f(itemDetails.getCamera_f());
        itemDetailsDtoV2.setCamera_b(itemDetails.getCamera_b());
        itemDetailsDtoV2.setCpu(itemDetails.getCpu());
        itemDetailsDtoV2.setGpu(itemDetails.getGpu());
        itemDetailsDtoV2.setNfc(itemDetails.getNfc());
        itemDetailsDtoV2.setDiagonal(itemDetails.getDiagonal());
        itemDetailsDtoV2.setNotes(itemDetails.getNotes());
        itemDetailsDtoV2.setStatus(itemDetails.getStatus());


        return itemDetailsDtoV2;
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
