package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.RoomDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface RoomService {

    RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO);

    List<RoomDTO> getAllRoomsInHotel(Long hotelId);

    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

    RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDto);
}
