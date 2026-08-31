package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.BookingGuest;
import com.sam.project.AirBnb.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingGuestRepository extends JpaRepository<BookingGuest, Long> {

    List<BookingGuest> findByGuest(Guest guest);
}
