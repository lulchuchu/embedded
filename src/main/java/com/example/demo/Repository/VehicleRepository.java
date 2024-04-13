package com.example.demo.Repository;

import com.example.demo.Modal.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findByUserId(Long userId);
}
