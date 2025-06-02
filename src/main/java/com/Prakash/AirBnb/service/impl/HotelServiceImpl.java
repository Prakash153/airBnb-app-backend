package com.Prakash.AirBnb.service.impl;

import com.Prakash.AirBnb.dto.HotelDto;
import com.Prakash.AirBnb.dto.HotelInfoDto;
import com.Prakash.AirBnb.dto.RoomDto;
import com.Prakash.AirBnb.entity.Hotel;
import com.Prakash.AirBnb.entity.Room;
import com.Prakash.AirBnb.exception.ResourceNotFoundException;
import com.Prakash.AirBnb.repository.HotelRepository;
import com.Prakash.AirBnb.repository.RoomRepository;
import com.Prakash.AirBnb.service.HotelSerive;
import com.Prakash.AirBnb.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelSerive {

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;


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
        log.info("Deleting the hotel with ID {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with ID: " + id));

        log.info(" Successfully deleted the hotel with ID {}", id);


        for (Room room : hotel.getRooms()) {
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("activating the hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with ID: " + hotelId));

        hotel.setActive(true);

        // assuming only do it once
        for (Room room : hotel.getRooms()) {
            inventoryService.initializeRoomFoAYear(room);
        }
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with ID: " + hotelId));

        List<RoomDto> rooms = hotel.getRooms()
                .stream()
                .map(element -> modelMapper.map(element, RoomDto.class))
                .toList();
        // hotel info dto has two fields hotel and rooms
        // filling those fields through allArgsConstructor
        return new HotelInfoDto(modelMapper.map(hotel, HotelDto.class), rooms);
    }
}
