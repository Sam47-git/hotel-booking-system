package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDTO {

    private Hotel hotel;
    private Double price;
}
