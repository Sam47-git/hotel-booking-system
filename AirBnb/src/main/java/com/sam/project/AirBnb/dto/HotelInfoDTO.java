package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDTO {

    private HotelDTO hotel;
    private List<RoomDTO> rooms;
}
