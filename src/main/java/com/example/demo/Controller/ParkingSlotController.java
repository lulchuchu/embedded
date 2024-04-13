package com.example.demo.Controller;

import com.example.demo.Service.ParkingSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingSlot")
@CrossOrigin
public class ParkingSlotController {
    private final ParkingSlotService parkingSlotService;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllParkingSlots() {
        return ResponseEntity.ok(parkingSlotService.getAllParkingSlots());
    }

    @GetMapping("/isOccupied/{isOccupied}")
    public ResponseEntity<?> getParkingSlotsIsOccupied(boolean isOccupied) {
        return ResponseEntity.ok(parkingSlotService.getParkingSlotsIsOccupied(isOccupied));
    }
}
