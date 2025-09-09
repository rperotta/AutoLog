package com.rperotta.autolog.service;

import com.rperotta.autolog.dto.VehicleCreationDTO;
import com.rperotta.autolog.dto.VehicleResponseDTO;
import com.rperotta.autolog.entity.DistanceUnit;
import com.rperotta.autolog.entity.User;
import com.rperotta.autolog.entity.Vehicle;
import com.rperotta.autolog.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserService userService;


    public VehicleResponseDTO addVehicle(VehicleCreationDTO vehicleDto) {
        User owner = userService.getUserById(vehicleDto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + vehicleDto.getOwnerId()));

        Vehicle newVehicle = new Vehicle();
        newVehicle.setMake(vehicleDto.getMake());
        newVehicle.setModel(vehicleDto.getModel());
        newVehicle.setYear(vehicleDto.getYear());
        newVehicle.setLicensePlate(vehicleDto.getLicensePlate());
        newVehicle.setMileage(vehicleDto.getMileage());
        newVehicle.setDistanceUnit(DistanceUnit.valueOf(vehicleDto.getDistanceUnit()));

        newVehicle.setOwner(owner);

        Vehicle savedVehicle = vehicleRepository.save(newVehicle);

        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setId(savedVehicle.getId());
        responseDTO.setMake(savedVehicle.getMake());
        responseDTO.setModel(savedVehicle.getModel());
        responseDTO.setYear(savedVehicle.getYear());
        responseDTO.setLicensePlate(savedVehicle.getLicensePlate());
        responseDTO.setMileage(savedVehicle.getMileage());
        responseDTO.setDistanceUnit(String.valueOf(savedVehicle.getDistanceUnit()));
        responseDTO.setOwnerId(savedVehicle.getOwner().getId());

        return responseDTO;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}

