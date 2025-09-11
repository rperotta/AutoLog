package com.rperotta.autolog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OdometerEntryCreationDTO {

    @NotNull(message = "Mileage cannot be null")
    @Positive(message = "Mileage must be a positive number")
    private Integer mileage;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotBlank(message = "Tire type cannot be blank")
    private String tireType;

    @NotNull(message = "Vehicle ID cannot be null")
    @Positive(message = "Vehicle ID must be a positive number")
    private Long vehicleId;

    private String notes;
}
