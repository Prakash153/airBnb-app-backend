package com.Prakash.AirBnb.service.impl;

import com.Prakash.AirBnb.dto.RoomDto;
import com.Prakash.AirBnb.entity.Hotel;
import com.Prakash.AirBnb.entity.Room;
import com.Prakash.AirBnb.exception.ResourceNotFoundException;
import com.Prakash.AirBnb.repository.HotelRepository;
import com.Prakash.AirBnb.repository.RoomRepository;
import com.Prakash.AirBnb.service.InventoryService;
import com.Prakash.AirBnb.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("creating a new room in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException(("Hotel Not found with ID: " + hotelId)));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        log.info("created a new room successfully in hotel with ID: {}", hotelId);
        // if the hotel is active then only we initialize the inventory
        if (hotel.getActive()) {
            inventoryService.initializeRoomFoAYear(room);
        }
        return modelMapper.map(room, RoomDto.class);


    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with Id: {} ", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException(("Hotel Not found with ID: " + hotelId)));
        log.info("Successfully got  all rooms in a hotel with Id: {} ", hotelId);
        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("trying to get the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));
        log.info("Successfully got the room with ID: {}", roomId);
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("trying to delete the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));
        log.info("Successfully deleted the room with ID: {}", roomId);
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);


    }
}
