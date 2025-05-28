package com.Prakash.AirBnb.controller;

import com.Prakash.AirBnb.dto.RoomDto;
import com.Prakash.AirBnb.entity.Room;
import com.Prakash.AirBnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto){
        RoomDto room =  roomService.createNewRoom(hotelId,roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
        List<RoomDto> roomDtoList = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok(roomDtoList);
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId){

        return ResponseEntity.ok( roomService.getRoomById(roomId));
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId){
       roomService.deleteRoomById(roomId);
        return  ResponseEntity.noContent().build();
        // TODO: Delete all future inventories for this room
    }

}
