package com.Prakash.AirBnb.controller;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.dto.HotelInfoDto;
import com.Prakash.AirBnb.dto.HotelSearchRequest;
import com.Prakash.AirBnb.service.HotelSerive;
import com.Prakash.AirBnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelSerive hotelSerive;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelSerive.getHotelInfoById(hotelId));
    }
}
