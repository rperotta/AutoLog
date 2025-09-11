package com.rperotta.autolog.dto;

import com.rperotta.autolog.entity.TireType;
import com.rperotta.autolog.entity.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OdometerEntryResponseDTO {

    private Long id;
    private int mileage;
    private LocalDate date;
    private String tireType;
    private Long vehicleId;
    private String notes;
}
