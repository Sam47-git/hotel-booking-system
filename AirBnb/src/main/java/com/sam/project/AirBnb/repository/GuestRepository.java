package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {


}
