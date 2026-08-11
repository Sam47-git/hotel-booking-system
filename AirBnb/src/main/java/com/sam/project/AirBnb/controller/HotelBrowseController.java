package com.sam.project.AirBnb.controller;

import com.sam.project.AirBnb.dto.HotelDTO;
import com.sam.project.AirBnb.dto.HotelInfoDTO;
import com.sam.project.AirBnb.dto.HotelPriceDTO;
import com.sam.project.AirBnb.dto.HotelSearchRequest;
import com.sam.project.AirBnb.service.HotelService;
import com.sam.project.AirBnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDTO>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        var page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
