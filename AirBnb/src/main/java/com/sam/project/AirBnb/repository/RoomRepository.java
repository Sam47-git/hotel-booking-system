package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
