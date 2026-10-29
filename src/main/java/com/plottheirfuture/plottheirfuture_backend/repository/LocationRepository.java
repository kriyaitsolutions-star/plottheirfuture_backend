package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByUserId(Long userId);

    List<Location> findByUserIdAndActiveTrue(Long userId);

    List<Location> findByUserIdOrderByPriorityAsc(Long userId);
}
