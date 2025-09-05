package com.rperotta.autolog.repository;

import com.rperotta.autolog.entity.OdometerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdometerEntryRepository extends JpaRepository <OdometerEntry, Long>{
    List<OdometerEntry> findByVehicleIdOrderByDateDesc(Long vehicleId);
}
