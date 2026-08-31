package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPriceDTO {

    private Room room;
    private Double price;
}
