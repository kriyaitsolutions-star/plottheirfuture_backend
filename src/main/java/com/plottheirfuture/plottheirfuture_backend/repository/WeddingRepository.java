package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.Wedding;
import com.plottheirfuture.plottheirfuture_backend.entity.WeddingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    Optional<Wedding> findBySlug(String slug);

    boolean existsBySlug(String slug);

    List<Wedding> findByStatus(WeddingStatus status);
}
