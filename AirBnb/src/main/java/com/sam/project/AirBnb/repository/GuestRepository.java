package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Guest;
import com.sam.project.AirBnb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findByUser(User user);
}
