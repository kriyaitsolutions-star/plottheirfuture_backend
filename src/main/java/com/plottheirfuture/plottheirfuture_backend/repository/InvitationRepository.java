package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByWeddingId(Long weddingId);

    Optional<Invitation> findByWeddingIdAndPhoneE164(Long weddingId, String phoneE164);

    List<Invitation> findByPhoneE164(String phoneE164);
}
