package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Hotel;
import com.sam.project.AirBnb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByOwner(User user);
}
