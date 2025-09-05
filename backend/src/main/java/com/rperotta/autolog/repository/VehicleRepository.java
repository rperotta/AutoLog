package com.rperotta.autolog.repository;

import com.rperotta.autolog.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository <Vehicle, Long>{
}
