package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.HotelContactInfo;
import lombok.Data;


@Data
public class HotelDTO {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;

}
