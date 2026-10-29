package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanTile;
import com.plottheirfuture.plottheirfuture_backend.entity.TileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorPlanTileRepository extends JpaRepository<FloorPlanTile, Long> {

    List<FloorPlanTile> findByWeddingId(Long weddingId);

    List<FloorPlanTile> findByWeddingIdAndZoneId(Long weddingId, Long zoneId);

    List<FloorPlanTile> findByWeddingIdAndStatus(Long weddingId, TileStatus status);
}
