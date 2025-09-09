package com.rperotta.autolog.controller;

import com.rperotta.autolog.entity.Vehicle;
import com.rperotta.autolog.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicle API", description = "Operations for managing user vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Returns the list of all registered vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping
    @Operation(summary = "Add a new vehicle", description = "Creates and saves a new vehicle for the current user")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a vehicle by ID", description = "Returns vehicle details by its ID")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle", description = "Removes a vehicle from the database by its ID")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
