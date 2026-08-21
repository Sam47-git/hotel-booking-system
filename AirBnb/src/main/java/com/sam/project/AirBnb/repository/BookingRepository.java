package com.sam.project.AirBnb.repository;


import com.sam.project.AirBnb.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    Optional<Booking> findByPaymentSessionId(String sessionId);
}
