package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.City;
import com.spring.model.ItemDetails;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDetailsDtoV2 {

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

    private String notes;

    private Status status;


    public static AdminItemDetailsDtoV2 from(ItemDetails itemDetails) {
        AdminItemDetailsDtoV2 adminItemDetailsDtoV2 = new AdminItemDetailsDtoV2();

        adminItemDetailsDtoV2.setId(itemDetails.getId());
        adminItemDetailsDtoV2.setPrice(itemDetails.getPrice());
        adminItemDetailsDtoV2.setCamera_f(itemDetails.getCamera_f());
        adminItemDetailsDtoV2.setCamera_b(itemDetails.getCamera_b());
        adminItemDetailsDtoV2.setCpu(itemDetails.getCpu());
        adminItemDetailsDtoV2.setGpu(itemDetails.getGpu());
        adminItemDetailsDtoV2.setNfc(itemDetails.getNfc());
        adminItemDetailsDtoV2.setDiagonal(itemDetails.getDiagonal());
        adminItemDetailsDtoV2.setNotes(itemDetails.getNotes());
        adminItemDetailsDtoV2.setStatus(itemDetails.getStatus());


        return adminItemDetailsDtoV2;
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
