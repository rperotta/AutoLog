package com.rperotta.autolog.controller;

import com.rperotta.autolog.entity.ServiceRecord;
import com.rperotta.autolog.service.ServiceRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-records")
@Tag(name = "Service Records", description = "Operations related to service records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    @Operation(summary = "Add a new service record")
    public ResponseEntity<ServiceRecord> addServiceRecord(@RequestBody ServiceRecord record) {
        return ResponseEntity.ok(serviceRecordService.addServiceRecord(record));
    }

    @GetMapping
    @Operation(summary = "Get all service records")
    public ResponseEntity<List<ServiceRecord>> getAllServiceRecords() {
        return ResponseEntity.ok(serviceRecordService.getAllServiceRecords());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a service record by ID")
    public ResponseEntity<ServiceRecord> getServiceRecordById(@PathVariable Long id) {
        return serviceRecordService.getServiceRecordById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
