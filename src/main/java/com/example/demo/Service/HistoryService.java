package com.example.demo.Service;

import com.example.demo.Exception.AlreadyExistException;
import com.example.demo.Modal.CheckInOutType;
import com.example.demo.Modal.History;
import com.example.demo.Modal.Vehicle;
import com.example.demo.Repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final VehicleService vehicleService;

    public HistoryService(HistoryRepository historyRepository, VehicleService vehicleService) {
        this.historyRepository = historyRepository;
        this.vehicleService = vehicleService;
    }

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    public List<History> getHistoriesByVehicleId(Long vehicleId) {
        return historyRepository.findByVehicleId(vehicleId);
    }

    public List<History> getHistoriesByCheckInOutType(CheckInOutType checkInOutType) {
        return historyRepository.findByCheckInOutType(checkInOutType);
    }

    public void checkIn(Long vehicleId, LocalDateTime checkInTime) {
        History latestHistory = findLatestHistoryByVehicleId(vehicleId);
        if (latestHistory != null && latestHistory.getCheckInOutType() == CheckInOutType.CHECKIN) {
            throw new AlreadyExistException("Vehicle is already checked in");
        }
        History history = new History();
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        history.setVehicle(vehicle);
        history.setCheckInOutTime(checkInTime);
        history.setCheckInOutType(CheckInOutType.CHECKIN);
        historyRepository.save(history);
    }

    public void checkOut(Long vehicleId, LocalDateTime checkOutTime) {
        History latestHistory = findLatestHistoryByVehicleId(vehicleId);
        if (latestHistory == null || latestHistory.getCheckInOutType() == CheckInOutType.CHECKOUT) {
            throw new AlreadyExistException("Vehicle is already checked out");
        }
        History history = new History();
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        history.setVehicle(vehicle);
        history.setCheckInOutTime(checkOutTime);
        history.setCheckInOutType(CheckInOutType.CHECKOUT);
        historyRepository.save(history);
    }

    public History findLatestHistoryByVehicleId(Long vehicleId) {
        return historyRepository.findByVehicleId(vehicleId).stream().reduce((first, second) -> second).orElse(null);
    }

}
