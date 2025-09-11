package com.rperotta.autolog.controller;

import com.rperotta.autolog.dto.VehicleCreationDTO;
import com.rperotta.autolog.dto.VehicleResponseDTO;
import com.rperotta.autolog.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicle API", description = "Operations for managing user vehicles")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Returns the list of all registered vehicles")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    @Operation(summary = "Add a new vehicle", description = "Creates and saves a new vehicle for the current user")
    public ResponseEntity<VehicleResponseDTO> addVehicle(@RequestBody VehicleCreationDTO vehicleCreationDTO) {
        VehicleResponseDTO savedVehicle = vehicleService.addVehicle(vehicleCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a vehicle by ID", description = "Returns vehicle details by its ID")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle", description = "Removes a vehicle from the database by its ID")
    //TODO: send a response about the deletion (success/didn't find the vehicle)
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
