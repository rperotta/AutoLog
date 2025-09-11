package com.rperotta.autolog.service;

import com.rperotta.autolog.dto.VehicleCreationDTO;
import com.rperotta.autolog.dto.VehicleResponseDTO;
import com.rperotta.autolog.entity.DistanceUnit;
import com.rperotta.autolog.entity.User;
import com.rperotta.autolog.entity.Vehicle;
import com.rperotta.autolog.exception.VehicleNotFoundException;
import com.rperotta.autolog.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserService userService;


    public VehicleResponseDTO addVehicle(VehicleCreationDTO vehicleDto) {
        Vehicle newVehicle = buildVehicle(vehicleDto);
        Vehicle savedVehicle = vehicleRepository.save(newVehicle);
        return toVehicleResponseDTO(savedVehicle);
    }

    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::toVehicleResponseDTO)
                .toList();
    }

    public VehicleResponseDTO getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(this::toVehicleResponseDTO)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));
    }

    public Vehicle getVehicleEntityById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle buildVehicle(VehicleCreationDTO vehicleDto) {
        User owner = userService.getUserEntityById(vehicleDto.getOwnerId());
        Vehicle newVehicle = new Vehicle();
        newVehicle.setMake(vehicleDto.getMake());
        newVehicle.setModel(vehicleDto.getModel());
        newVehicle.setYear(vehicleDto.getYear());
        newVehicle.setLicensePlate(vehicleDto.getLicensePlate());
        newVehicle.setMileage(vehicleDto.getMileage());
        newVehicle.setDistanceUnit(DistanceUnit.valueOf(vehicleDto.getDistanceUnit()));
        newVehicle.setOwner(owner);
        return newVehicle;
    }

    public VehicleResponseDTO toVehicleResponseDTO(Vehicle savedVehicle) {
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
}

