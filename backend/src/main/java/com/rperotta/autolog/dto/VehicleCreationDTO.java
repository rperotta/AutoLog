package com.rperotta.autolog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class VehicleCreationDTO {

    @NotBlank(message = "Make cannot be blank")
    private String make;

    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotNull(message = "Year cannot be null")
    @Positive(message = "Year must be a positive number")
    private int year;

    @NotBlank(message = "License plate cannot be blank")
    private String licensePlate;

    @NotNull(message = "Mileage cannot be null")
    @Positive(message = "Mileage must be a positive number")
    private int mileage;

    @NotBlank(message = "Distance unit cannot be blank")
    private String distanceUnit;

    @NotNull(message = "Owner ID cannot be null")
    @Positive(message = "Owner ID must be a positive number")
    private Long ownerId;
}
