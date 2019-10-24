package com.spring.dto.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.ItemDetails;
import com.spring.model.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDetailsDto {

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


    public static AdminItemDetailsDto from(ItemDetails itemDetails) {
        AdminItemDetailsDto adminItemDetailsDto = new AdminItemDetailsDto();

        adminItemDetailsDto.setId(itemDetails.getId());
        adminItemDetailsDto.setPrice(itemDetails.getPrice());
        adminItemDetailsDto.setCamera_f(itemDetails.getCamera_f());
        adminItemDetailsDto.setCamera_b(itemDetails.getCamera_b());
        adminItemDetailsDto.setCpu(itemDetails.getCpu());
        adminItemDetailsDto.setGpu(itemDetails.getGpu());
        adminItemDetailsDto.setNfc(itemDetails.getNfc());
        adminItemDetailsDto.setDiagonal(itemDetails.getDiagonal());
        adminItemDetailsDto.setNotes(itemDetails.getNotes());
        adminItemDetailsDto.setStatus(itemDetails.getStatus());


        return adminItemDetailsDto;
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
