package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.HotelDTO;
import com.sam.project.AirBnb.entities.Hotel;
import com.sam.project.AirBnb.entities.Room;
import com.sam.project.AirBnb.exception.ResourceNotFoundException;
import com.sam.project.AirBnb.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDTO) {
        log.info("Creating new hotel with name: {}", hotelDTO.getName());
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created new hotel with id: {}", hotelDTO.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with id: {}"+ id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ id));
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating hotel information with id: {}"+ id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ id));
        modelMapper.map(hotelDTO, hotel); // update info
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ id));

        hotelRepository.deleteById(id);

        for (Room room: hotel.getRooms()) {
            inventoryService.deleteFutureInventories(room);
        }
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating hotel information with id: {}"+ hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ hotelId));
        hotel.setActive(true);

        for (Room room: hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }

}
