package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorPlanZoneRepository extends JpaRepository<FloorPlanZone, Long> {

    List<FloorPlanZone> findByWeddingId(Long weddingId);
}
