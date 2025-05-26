package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.entity.Hotel;
import com.Prakash.AirBnb.exception.ResourceNotFoundException;
import com.Prakash.AirBnb.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelSerive {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;


    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("creating a new Hotel with name {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("created a new hotel with ID {}", hotel.getId());

        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info(" getting the hotel with ID {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Hotel Not found with ID: " + id)));
        return modelMapper.map(hotel, HotelDto.class);
    }

    public HotelDto updateHotelById(Long id ,HotelDto hotelDto) {
        log.info(" updating the hotel with ID {}", id);

        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Hotel Not found with ID: " + id)));
        modelMapper.map(hotelDto, hotel);

      hotel =  hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }
}
