package com.spring.dto.V1.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.ItemDetails;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDetailsDtoV1 {

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


    public static AdminItemDetailsDtoV1 from(ItemDetails itemDetails) {
        AdminItemDetailsDtoV1 adminItemDetailsDtoV1 = new AdminItemDetailsDtoV1();

        adminItemDetailsDtoV1.setId(itemDetails.getId());
        adminItemDetailsDtoV1.setPrice(itemDetails.getPrice());
        adminItemDetailsDtoV1.setCamera_f(itemDetails.getCamera_f());
        adminItemDetailsDtoV1.setCamera_b(itemDetails.getCamera_b());
        adminItemDetailsDtoV1.setCpu(itemDetails.getCpu());
        adminItemDetailsDtoV1.setGpu(itemDetails.getGpu());
        adminItemDetailsDtoV1.setNfc(itemDetails.getNfc());
        adminItemDetailsDtoV1.setDiagonal(itemDetails.getDiagonal());
        adminItemDetailsDtoV1.setNotes(itemDetails.getNotes());
        adminItemDetailsDtoV1.setStatus(itemDetails.getStatus());


        return adminItemDetailsDtoV1;
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
