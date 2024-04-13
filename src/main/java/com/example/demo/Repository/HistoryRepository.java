package com.example.demo.Repository;

import com.example.demo.Modal.CheckInOutType;
import com.example.demo.Modal.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByVehicleId(Long vehicleId);
    List<History> findByCheckInOutType(CheckInOutType checkInOutType);
}
