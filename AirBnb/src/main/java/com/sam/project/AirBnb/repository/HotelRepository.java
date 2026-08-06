package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
