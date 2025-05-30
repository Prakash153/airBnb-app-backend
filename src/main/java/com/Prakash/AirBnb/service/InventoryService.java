package com.Prakash.AirBnb.service;

import com.Prakash.AirBnb.entity.Room;

public interface InventoryService {
    void initializeRoomFoAYear(Room room);
    void deleteFutureInventories(Room room);
}
