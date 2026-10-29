package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.Contribution;
import com.plottheirfuture.plottheirfuture_backend.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    List<Contribution> findByWeddingId(Long weddingId);

    List<Contribution> findByWeddingIdAndPaymentStatus(Long weddingId, PaymentStatus paymentStatus);
}
