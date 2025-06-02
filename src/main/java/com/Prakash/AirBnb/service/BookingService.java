package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.dto.BookingDto;
import com.Prakash.AirBnb.dto.BookingRequest;
import com.Prakash.AirBnb.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);


    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
