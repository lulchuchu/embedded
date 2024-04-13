package com.example.demo.Controller;

import com.example.demo.Security.UserDetail;
import com.example.demo.Service.VehicleService;
import com.example.demo.entity.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addVehicle(Authentication authentication, @RequestBody Vehicle vehicle) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        vehicleService.addVehicle(vehicle, userDetail.getUser().getId());
        return ResponseEntity.ok("Vehicle added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByUserId(userId));
    }

    @GetMapping("/licensePlate/{licensePlate}")
    public ResponseEntity<?> getVehiclesByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleService.getVehiclesByLicensePalate(licensePlate));
    }


}
