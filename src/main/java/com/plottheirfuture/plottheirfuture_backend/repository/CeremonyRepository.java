package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.Ceremony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CeremonyRepository extends JpaRepository<Ceremony, Long> {

    List<Ceremony> findByWeddingIdOrderByEventDateAscStartTimeAsc(Long weddingId);
}
