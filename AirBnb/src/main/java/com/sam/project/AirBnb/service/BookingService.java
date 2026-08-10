package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.BookingDTO;
import com.sam.project.AirBnb.dto.BookingRequest;
import com.sam.project.AirBnb.dto.GuestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {

    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDTOList);
}
