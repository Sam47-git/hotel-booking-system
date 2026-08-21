package com.sam.project.AirBnb.controller;

import com.sam.project.AirBnb.dto.BookingDTO;
import com.sam.project.AirBnb.dto.BookingRequest;
import com.sam.project.AirBnb.dto.GuestDTO;
import com.sam.project.AirBnb.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDTO> initialiseBooking(@RequestBody BookingRequest bookingRequest) {
        return  ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    private ResponseEntity<BookingDTO> addGuests(@PathVariable Long bookingId,
                                                 @RequestBody List<GuestDTO> guestDTOList) {
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDTOList));
    }

    @PostMapping("/{bookingId}/payments")
    private ResponseEntity<Map<String, String>> initiatePayment(@PathVariable Long bookingId) {
        String sessionUrl = bookingService.initiatePayments(bookingId);
        return ResponseEntity.ok(Map.of("sessionUrl", sessionUrl));
    }

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bookingId}/status")
    public ResponseEntity<Map<String, String>> getBookingStatus(@PathVariable Long bookingId) {
        return ResponseEntity.ok(Map.of("status", bookingService.getBookingStatus(bookingId)));
    }
}
