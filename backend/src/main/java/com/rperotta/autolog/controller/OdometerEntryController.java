package com.rperotta.autolog.controller;

import com.rperotta.autolog.entity.OdometerEntry;
import com.rperotta.autolog.service.OdometerEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Add a new odometer entry")
    public ResponseEntity<OdometerEntry> addOdometerEntry(@RequestBody OdometerEntry entry) {
        return ResponseEntity.ok(odometerEntryService.addOdometerEntry(entry));
    }

    @GetMapping
    @Operation(summary = "Get all odometer entries")
    public ResponseEntity<List<OdometerEntry>> getAllOdometerEntries() {
        return ResponseEntity.ok(odometerEntryService.getAllOdometerEntries());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an odometer entry by ID")
    public ResponseEntity<OdometerEntry> getOdometerEntryById(@PathVariable Long id) {
        return odometerEntryService.getOdometerEntryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
