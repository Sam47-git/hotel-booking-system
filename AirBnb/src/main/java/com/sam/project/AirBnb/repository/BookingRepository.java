package com.sam.project.AirBnb.repository;


import com.sam.project.AirBnb.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {


}
