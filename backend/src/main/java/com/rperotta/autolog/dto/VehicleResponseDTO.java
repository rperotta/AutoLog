package com.rperotta.autolog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class VehicleResponseDTO {

    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private int mileage;
    private String distanceUnit;
    private Long ownerId;
    //private List<ServiceRecordResponseDTO> serviceRecords; // DTO per i ServiceRecord
    //private List<OdometerEntryResponseDTO> odometerEntries; // DTO per gli OdometerEntry
}
