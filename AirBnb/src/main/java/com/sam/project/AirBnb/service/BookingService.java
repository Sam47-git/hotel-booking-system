package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.BookingDTO;
import com.sam.project.AirBnb.dto.BookingRequest;
import com.sam.project.AirBnb.dto.GuestDTO;
import com.sam.project.AirBnb.dto.HotelReportDTO;
import com.sam.project.AirBnb.entities.enums.BookingStatus;
import com.stripe.model.Event;
import org.jspecify.annotations.Nullable;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDTOList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDTO getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDTO> getMyBookings();
}
