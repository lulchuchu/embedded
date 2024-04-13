package com.example.demo.Service;

import com.example.demo.Modal.ParkingSlot;
import com.example.demo.Repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    public List<ParkingSlot> getParkingSlotsIsOccupied(boolean isOccupied) {
        return parkingSlotRepository.findByIsOccupied(isOccupied);
    }
}
