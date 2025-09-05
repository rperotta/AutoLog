package com.rperotta.autolog.service;

import com.rperotta.autolog.entity.OdometerEntry;
import com.rperotta.autolog.repository.OdometerEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdometerEntryService {
    private final OdometerEntryRepository odometerEntryRepository;

    public OdometerEntryService(OdometerEntryRepository odometerEntryRepository) {
        this.odometerEntryRepository = odometerEntryRepository;
    }

    public OdometerEntry addOdometerEntry(OdometerEntry entry) {
        return odometerEntryRepository.save(entry);
    }

    public List<OdometerEntry> getAllOdometerEntries() {
        return odometerEntryRepository.findAll();
    }

    public Optional<OdometerEntry> getOdometerEntryById(Long id) {
        return odometerEntryRepository.findById(id);
    }

    public void deleteOdometerEntry(Long id) {
        odometerEntryRepository.deleteById(id);
    }

    public List<OdometerEntry> getEntriesByVehicle(Long vehicleId) {
        return odometerEntryRepository.findByVehicleIdOrderByDateDesc(vehicleId);
    }
}

