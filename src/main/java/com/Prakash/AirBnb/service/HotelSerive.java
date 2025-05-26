package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.entity.Hotel;

public interface HotelSerive {
  public   HotelDto  createNewHotel(HotelDto hotelDto);
  public HotelDto getHotelById(Long id );
  public HotelDto updateHotelById(Long id, HotelDto hotelDto);
}
