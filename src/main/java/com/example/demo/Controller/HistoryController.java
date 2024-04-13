package com.example.demo.Controller;

import com.example.demo.Modal.CheckInOutType;
import com.example.demo.Service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/history")
@CrossOrigin
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllHistories() {
        return ResponseEntity.ok(historyService.getAllHistories());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> getHistoriesByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(historyService.getHistoriesByVehicleId(vehicleId));
    }

    @GetMapping("/checkInOutType/{checkInOutType}")
    public ResponseEntity<?> getHistoriesByCheckInOutType(@PathVariable String checkInOutType) {
        return ResponseEntity.ok(historyService.getHistoriesByCheckInOutType(CheckInOutType.valueOf(checkInOutType)));
    }

    @PostMapping("/checkIn/{vehicleId}")
    public ResponseEntity<?> checkIn(@PathVariable Long vehicleId) {
        LocalDateTime checkInTime = LocalDateTime.now();
        historyService.checkIn(vehicleId, checkInTime);
        return ResponseEntity.ok("Check in successfully");
    }

    @PostMapping("/checkOut/{vehicleId}")
    public ResponseEntity<?> checkOut(@PathVariable Long vehicleId) {
        LocalDateTime checkOutTime = LocalDateTime.now();
        historyService.checkOut(vehicleId, checkOutTime);
        return ResponseEntity.ok("Check out successfully");
    }
}
