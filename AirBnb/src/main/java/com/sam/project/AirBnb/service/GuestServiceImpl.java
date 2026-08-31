package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.GuestDTO;
import com.sam.project.AirBnb.entities.BookingGuest;
import com.sam.project.AirBnb.entities.Guest;
import com.sam.project.AirBnb.entities.User;
import com.sam.project.AirBnb.repository.BookingGuestRepository;
import com.sam.project.AirBnb.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sam.project.AirBnb.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final BookingGuestRepository bookingGuestRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GuestDTO> getAllGuests() {
        User user = getCurrentUser();
        log.info("Fetching all guests of user with id: {}", user.getId());
        List<Guest> guests = guestRepository.findByUser(user);
        return guests.stream()
                .map(guest -> modelMapper.map(guest, GuestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GuestDTO addNewGuest(GuestDTO guestDto) {
        log.info("Adding new guest: {}", guestDto);
        User user = getCurrentUser();
        Guest guest = modelMapper.map(guestDto, Guest.class);
        guest.setUser(user);
        Guest savedGuest = guestRepository.save(guest);
        log.info("Guest added with ID: {}", savedGuest.getId());
        return modelMapper.map(savedGuest, GuestDTO.class);
    }

    @Override
    public void updateGuest(Long guestId, GuestDTO guestDto) {
        log.info("Updating guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));

        User user = getCurrentUser();
        if(!user.getId().equals(guest.getUser().getId()))
            throw new AccessDeniedException("You are not the owner of this guest");

        modelMapper.map(guestDto, guest);
        guest.setUser(user);
        guest.setId(guestId);

        guestRepository.save(guest);
        log.info("Guest with ID: {} updated successfully", guestId);
    }

    @Override
    @Transactional
    public void deleteGuest(Long guestId) {
        log.info("Deleting guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));

        User user = getCurrentUser();
        if(!user.getId().equals(guest.getUser().getId()))
            throw new AccessDeniedException("You are not the owner of this guest");

        List<BookingGuest> bookingGuests =
                bookingGuestRepository.findByGuest(guest);

        for (BookingGuest bookingGuest : bookingGuests) {
            bookingGuest.setGuest(null);
        }

        bookingGuestRepository.saveAll(bookingGuests);

        guestRepository.delete(guest);

        log.info("Guest with ID: {} deleted successfully", guestId);
    }
}
