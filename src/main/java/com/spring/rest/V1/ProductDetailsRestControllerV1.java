//package com.spring.rest;
//
//import com.spring.dto.ByIdRequestDto;
//import com.spring.dto.ItemDetailsDto;
//import com.spring.model.ItemDetails;
//import com.spring.service.ItemDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/api/v1/env/")
//public class ProductDetailsRestControllerV1 {
//
//    @Autowired
//    ItemDetailsService itemDetailsService;
//
//    @GetMapping("dproduct")
//    public ResponseEntity getProductDetailById(@RequestBody ByIdRequestDto byIdRequestDto) {
//        ItemDetails itemDetails = itemDetailsService.findById(byIdRequestDto.getId());
//        if (itemDetails == null) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(ItemDetailsDto.fromProductDetails(itemDetails));
//    }
//
//    @PostMapping("dproduct")
//    public ResponseEntity addProductDetail(@RequestBody ItemDetailsDto itemDetailsDto){
//        ItemDetails itemDetails = itemDetailsService.save(itemDetailsDto.toProductDetails());
//
//        if (itemDetails == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(ItemDetailsDto.fromProductDetails(itemDetails));
//    }
//
//
//}