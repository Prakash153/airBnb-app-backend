package com.Prakash.AirBnb.service.impl;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.entity.Hotel;
import com.Prakash.AirBnb.entity.Room;
import com.Prakash.AirBnb.exception.ResourceNotFoundException;
import com.Prakash.AirBnb.repository.HotelRepository;
import com.Prakash.AirBnb.service.HotelSerive;
import com.Prakash.AirBnb.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelSerive {

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
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

    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info(" updating the hotel with ID {}", id);

        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Hotel Not found with ID: " + id)));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        log.info("successfully updated the hotel with ID {}", id);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        log.info(" Trying to delete the hotel with ID {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with ID: " + id));

        log.info(" Successfully deleted the hotel with ID {}", id);
        hotelRepository.deleteById(id);

        for (Room room : hotel.getRooms()) {
            inventoryService. deleteFutureInventories(room);
        }

    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        log.info("activating the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with ID: " + id));

        hotel.setActive(true);

        // assuming only do it once
        for (Room room : hotel.getRooms()) {
            inventoryService.initializeRoomFoAYear(room);
        }
    }
}
