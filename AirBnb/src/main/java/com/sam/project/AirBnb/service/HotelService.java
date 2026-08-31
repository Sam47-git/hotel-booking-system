package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.HotelDTO;
import com.sam.project.AirBnb.dto.HotelInfoDTO;
import com.sam.project.AirBnb.dto.HotelInfoRequestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface HotelService {

    HotelDTO createNewHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);

    HotelDTO updateHotelById(Long id, HotelDTO hotelDTO);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);

    HotelInfoDTO getHotelInfoById(Long hotelId, HotelInfoRequestDTO hotelInfoRequestDTO);

    List<HotelDTO> getAllHotels();
}
