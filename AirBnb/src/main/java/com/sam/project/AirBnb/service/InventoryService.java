package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.HotelDTO;
import com.sam.project.AirBnb.dto.HotelPriceDTO;
import com.sam.project.AirBnb.dto.HotelSearchRequest;
import com.sam.project.AirBnb.entities.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);
}
