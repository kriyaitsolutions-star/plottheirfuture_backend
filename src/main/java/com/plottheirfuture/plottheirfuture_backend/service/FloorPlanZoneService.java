package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanZone;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.FloorPlanZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorPlanZoneService {

    private final FloorPlanZoneRepository floorPlanZoneRepository;

    public List<FloorPlanZone> getAllZones() {
        return floorPlanZoneRepository.findAll();
    }

    public FloorPlanZone getZoneById(Long id) {
        return floorPlanZoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Floor plan zone not found with id: " + id));
    }

    public List<FloorPlanZone> getZonesByWeddingId(Long weddingId) {
        return floorPlanZoneRepository.findByWeddingId(weddingId);
    }

    @Transactional
    public FloorPlanZone createZone(FloorPlanZone zone) {
        return floorPlanZoneRepository.save(zone);
    }

    @Transactional
    public FloorPlanZone updateZone(Long id, FloorPlanZone updated) {
        FloorPlanZone existing = getZoneById(id);

        if (updated.getRoomName() != null) existing.setRoomName(updated.getRoomName());
        if (updated.getTotalSft() != null) existing.setTotalSft(updated.getTotalSft());
        if (updated.getFundedSft() != null) existing.setFundedSft(updated.getFundedSft());
        if (updated.getSvgElementId() != null) existing.setSvgElementId(updated.getSvgElementId());

        return floorPlanZoneRepository.save(existing);
    }

    @Transactional
    public void deleteZone(Long id) {
        if (!floorPlanZoneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Floor plan zone not found with id: " + id);
        }
        floorPlanZoneRepository.deleteById(id);
    }
}
