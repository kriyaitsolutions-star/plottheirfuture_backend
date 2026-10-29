package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanTile;
import com.plottheirfuture.plottheirfuture_backend.entity.TileStatus;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.FloorPlanTileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorPlanTileService {

    private final FloorPlanTileRepository floorPlanTileRepository;

    public List<FloorPlanTile> getAllTiles() {
        return floorPlanTileRepository.findAll();
    }

    public FloorPlanTile getTileById(Long id) {
        return floorPlanTileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Floor plan tile not found with id: " + id));
    }

    public List<FloorPlanTile> getTilesByWeddingId(Long weddingId) {
        return floorPlanTileRepository.findByWeddingId(weddingId);
    }

    public List<FloorPlanTile> getTilesByWeddingAndZone(Long weddingId, Long zoneId) {
        return floorPlanTileRepository.findByWeddingIdAndZoneId(weddingId, zoneId);
    }

    public List<FloorPlanTile> getTilesByWeddingAndStatus(Long weddingId, TileStatus status) {
        return floorPlanTileRepository.findByWeddingIdAndStatus(weddingId, status);
    }

    @Transactional
    public FloorPlanTile createTile(FloorPlanTile tile) {
        return floorPlanTileRepository.save(tile);
    }

    @Transactional
    public FloorPlanTile updateTile(Long id, FloorPlanTile updated) {
        FloorPlanTile existing = getTileById(id);

        if (updated.getZoneId() != null) existing.setZoneId(updated.getZoneId());
        if (updated.getSftSize() != null) existing.setSftSize(updated.getSftSize());
        if (updated.getStatus() != null) existing.setStatus(updated.getStatus());
        if (updated.getLockedUntil() != null) existing.setLockedUntil(updated.getLockedUntil());
        if (updated.getLockedByPhone() != null) existing.setLockedByPhone(updated.getLockedByPhone());
        if (updated.getDonorId() != null) existing.setDonorId(updated.getDonorId());

        return floorPlanTileRepository.save(existing);
    }

    @Transactional
    public FloorPlanTile lockTile(Long id, String phone, int minutes) {
        FloorPlanTile tile = getTileById(id);
        if (tile.getStatus() == TileStatus.FUNDED) {
            throw new IllegalArgumentException("Tile is already funded.");
        }
        tile.setStatus(TileStatus.HOLD);
        tile.setLockedByPhone(phone);
        tile.setLockedUntil(LocalDateTime.now().plusMinutes(minutes));
        return floorPlanTileRepository.save(tile);
    }

    @Transactional
    public FloorPlanTile markFunded(Long id, Long donorId) {
        FloorPlanTile tile = getTileById(id);
        tile.setStatus(TileStatus.FUNDED);
        tile.setDonorId(donorId);
        tile.setLockedUntil(null);
        return floorPlanTileRepository.save(tile);
    }

    @Transactional
    public void deleteTile(Long id) {
        if (!floorPlanTileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Floor plan tile not found with id: " + id);
        }
        floorPlanTileRepository.deleteById(id);
    }
}
