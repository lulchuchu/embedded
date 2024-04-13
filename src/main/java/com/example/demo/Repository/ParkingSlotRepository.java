package com.example.demo.Repository;

import com.example.demo.Modal.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    public List<ParkingSlot> findByIsOccupied(boolean isOccupied);
}
