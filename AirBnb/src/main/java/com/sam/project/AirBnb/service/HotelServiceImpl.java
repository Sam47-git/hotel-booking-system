package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.*;
import com.sam.project.AirBnb.entities.Hotel;
import com.sam.project.AirBnb.entities.Room;
import com.sam.project.AirBnb.entities.User;
import com.sam.project.AirBnb.exception.ResourceNotFoundException;
import com.sam.project.AirBnb.exception.UnAuthorisedException;
import com.sam.project.AirBnb.repository.HotelRepository;
import com.sam.project.AirBnb.repository.InventoryRepository;
import com.sam.project.AirBnb.repository.RoomRepository;
import com.sam.project.AirBnb.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sam.project.AirBnb.util.AppUtils.getCurrentUser;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final UserRepository userRepository;

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDTO) {
        log.info("Creating new hotel with name: {}", hotelDTO.getName());
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setActive(false);

        User user = getCurrentUser();
        hotel.setOwner(user);

        hotel = hotelRepository.save(hotel);
        log.info("Created new hotel with id: {}", hotelDTO.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with id: "+ id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ id));

        User user = getCurrentUser();
        if(!user.getId().equals(hotel.getOwner().getId())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }

        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDTO) {
        log.info("Updating hotel information with id: "+ id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ id));

        User user = getCurrentUser();
        if (!user.getId().equals(hotel.getOwner().getId())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }
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

        User user = getCurrentUser();
        if(!user.getId().equals(hotel.getOwner().getId())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+id);
        }

        for (Room room: hotel.getRooms()) {
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating hotel information with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ hotelId));

        User user = getCurrentUser();
        if(!user.getId().equals(hotel.getOwner().getId())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+hotelId);
        }

        hotel.setActive(true);

        for (Room room: hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }

    @Override
    public HotelInfoDTO getHotelInfoById(Long hotelId, HotelInfoRequestDTO hotelInfoRequestDTO) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+ hotelId));

        long daysCount = ChronoUnit.DAYS.between(hotelInfoRequestDTO.getStartDate(), hotelInfoRequestDTO.getEndDate())+1;

        List<RoomPriceDTO> roomPriceDtoList = inventoryRepository.findRoomAveragePrice(hotelId,
                hotelInfoRequestDTO.getStartDate(), hotelInfoRequestDTO.getEndDate(),
                hotelInfoRequestDTO.getRoomsCount(), daysCount);

        List<RoomPriceResponseDTO> rooms = roomPriceDtoList.stream()
                .map(roomPriceDto -> {
                    RoomPriceResponseDTO roomPriceResponseDto =
                            modelMapper.map(roomPriceDto.getRoom(), RoomPriceResponseDTO.class);
                    roomPriceResponseDto.setPrice(roomPriceDto.getPrice());
                    return roomPriceResponseDto;
                })
                .collect(Collectors.toList());

        return new HotelInfoDTO(modelMapper.map(hotel, HotelDTO.class), rooms);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        User user = getCurrentUser();
        log.info("Getting all hotels for the admin user with id : "+ user.getId());
        List<Hotel> hotels = hotelRepository.findByOwner(user);
        return hotels.stream()
                .map((element) -> modelMapper.map(element, HotelDTO.class))
                .collect(Collectors.toList());
    }
}
