package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.entities.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
