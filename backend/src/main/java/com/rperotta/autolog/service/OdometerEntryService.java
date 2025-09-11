package com.rperotta.autolog.service;

import com.rperotta.autolog.dto.OdometerEntryCreationDTO;
import com.rperotta.autolog.dto.OdometerEntryResponseDTO;
import com.rperotta.autolog.entity.OdometerEntry;
import com.rperotta.autolog.entity.TireType;
import com.rperotta.autolog.entity.Vehicle;
import com.rperotta.autolog.repository.OdometerEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OdometerEntryService {
    private final OdometerEntryRepository odometerEntryRepository;
    private final VehicleService vehicleService;

    public OdometerEntryResponseDTO addOdometerEntry(OdometerEntryCreationDTO entryDTO) {
        OdometerEntry odometerEntry = buildOdometerEntry(entryDTO);
        return toOdometerEntryResponseDTO(odometerEntryRepository.save(odometerEntry));
    }

    public List<OdometerEntry> getAllOdometerEntries() {
        return odometerEntryRepository.findAll();
    }

    public Optional<OdometerEntry> getOdometerEntryById(Long id) {
        return odometerEntryRepository.findById(id);
    }

    public Optional<OdometerEntry> getOdometerEntriesByVehicleId(Long id) {
        return odometerEntryRepository.findById(id);
    }

    public void deleteOdometerEntry(Long id) {
        odometerEntryRepository.deleteById(id);
    }

    public List<OdometerEntry> getEntriesByVehicle(Long vehicleId) {
        return odometerEntryRepository.findByVehicleIdOrderByDateDesc(vehicleId);
    }

    public OdometerEntryResponseDTO updateOdometerEntry(Long id, OdometerEntryCreationDTO updateDTO) {
        return null;
    }

    private OdometerEntry buildOdometerEntry(OdometerEntryCreationDTO entryDTO){
        Vehicle vehicle = vehicleService.getVehicleEntityById(entryDTO.getVehicleId());
        OdometerEntry odometerEntry = new OdometerEntry();
        odometerEntry.setMileage(entryDTO.getMileage());
        odometerEntry.setDate(entryDTO.getDate());
        odometerEntry.setTireType(TireType.valueOf(entryDTO.getTireType()));
        odometerEntry.setVehicle(vehicle);
        odometerEntry.setNotes(entryDTO.getNotes());
        return odometerEntry;
    }

    private OdometerEntryResponseDTO toOdometerEntryResponseDTO(OdometerEntry entry) {
        OdometerEntryResponseDTO dto = new OdometerEntryResponseDTO();
        dto.setId(entry.getId());
        dto.setMileage(entry.getMileage());
        dto.setDate(entry.getDate());

        // Convert Enum to String safely
        dto.setTireType(entry.getTireType() != null ? entry.getTireType().name() : null);

        // Extract vehicle ID
        dto.setVehicleId(entry.getVehicle() != null ? entry.getVehicle().getId() : null);

        dto.setNotes(entry.getNotes());

        return dto;
    }
}

