package com.sam.project.AirBnb.repository;

import com.sam.project.AirBnb.entities.Inventory;
import com.sam.project.AirBnb.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);

}
