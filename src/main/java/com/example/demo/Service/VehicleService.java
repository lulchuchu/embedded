package com.example.demo.Service;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.Repository.VehicleRepository;
import com.example.demo.Modal.User;
import com.example.demo.Modal.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserServiceInterface userService;

    public VehicleService(VehicleRepository vehicleRepository, UserServiceInterface userService) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle addVehicle(Vehicle vehicle, Long userId) {
        User user = userService.getUser(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }

    public List<Vehicle> getVehiclesByLicensePalate(String type) {
        return vehicleRepository.findByLicensePlate(type);
    }
}
