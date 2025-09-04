package com.rperotta.autolog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private int mileage;

    @Enumerated(EnumType.STRING)
    private DistanceUnit distanceUnit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
}
