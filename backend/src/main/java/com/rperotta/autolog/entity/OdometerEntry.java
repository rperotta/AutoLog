package com.rperotta.autolog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "odometer_entries")
@Getter
@Setter
@NoArgsConstructor
public class OdometerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mileage;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TireType tireType;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private String notes;
}
