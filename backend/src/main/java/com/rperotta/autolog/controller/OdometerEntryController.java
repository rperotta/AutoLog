package com.rperotta.autolog.controller;

import com.rperotta.autolog.dto.OdometerEntryCreationDTO;
import com.rperotta.autolog.dto.OdometerEntryResponseDTO;
import com.rperotta.autolog.entity.OdometerEntry;
import com.rperotta.autolog.service.OdometerEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odometer-entries")
@Tag(name = "Odometer Entries", description = "Operations related to odometer entries")
public class OdometerEntryController {

    private final OdometerEntryService odometerEntryService;

    public OdometerEntryController(OdometerEntryService odometerEntryService) {
        this.odometerEntryService = odometerEntryService;
    }

    @PostMapping
    @Operation(summary = "Create a new odometer entry")
    public ResponseEntity<OdometerEntryResponseDTO> createOdometerEntry(
            @RequestBody OdometerEntryCreationDTO creationDTO) {
        OdometerEntryResponseDTO createdEntry = odometerEntryService.addOdometerEntry(creationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntry);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an odometer entry by ID")
    public ResponseEntity<OdometerEntryResponseDTO> getOdometerEntryById(@PathVariable Long id) {
        OdometerEntryResponseDTO entry = odometerEntryService.getOdometerEntryById(id);
        return ResponseEntity.ok(entry);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get all odometer entries for a vehicle")
    public ResponseEntity<List<OdometerEntryResponseDTO>> getOdometerEntriesByVehicle(@PathVariable Long vehicleId) {
        List<OdometerEntryResponseDTO> entries = odometerEntryService.getOdometerEntriesByVehicleId(vehicleId);
        return ResponseEntity.ok(entries);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing odometer entry")
    public ResponseEntity<OdometerEntryResponseDTO> updateOdometerEntry(
            @PathVariable Long id,
            @RequestBody OdometerEntryCreationDTO updateDTO) {
        OdometerEntryResponseDTO updatedEntry = odometerEntryService.updateOdometerEntry(id, updateDTO);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an odometer entry by ID")
    public void deleteOdometerEntry(@PathVariable Long id) {
        odometerEntryService.deleteOdometerEntry(id);
        // TODO: return a proper ResponseEntity with status and message
    }
}