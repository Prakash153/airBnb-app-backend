package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.dto.HotelSearchRequest;
import com.Prakash.AirBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomFoAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
