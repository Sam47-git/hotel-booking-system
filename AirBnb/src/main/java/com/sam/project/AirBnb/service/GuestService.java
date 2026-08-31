package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.GuestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface GuestService {


    List<GuestDTO> getAllGuests();

    GuestDTO addNewGuest(GuestDTO guestDto);

    void updateGuest(Long guestId, GuestDTO guestDto);

    void deleteGuest(Long guestId);
}
