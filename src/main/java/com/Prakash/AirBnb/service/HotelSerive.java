package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.dto.HotelDto;

public interface HotelSerive {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long id);
}
